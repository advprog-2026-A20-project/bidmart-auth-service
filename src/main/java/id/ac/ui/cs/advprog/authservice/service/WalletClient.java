package id.ac.ui.cs.advprog.authservice.service;

import id.ac.ui.cs.advprog.authservice.model.Role;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Component
public class WalletClient {

    private static final BigDecimal DEFAULT_BUYER_STARTING_BALANCE = new BigDecimal("1000000.00");
    private final RestTemplate restTemplate;
    private final String walletServiceBaseUrl;

    public WalletClient(
        RestTemplate restTemplate,
        @Value("${WALLET_SERVICE_BASE_URL:http://localhost:8085}") String walletServiceBaseUrl
    ) {
        this.restTemplate = restTemplate;
        this.walletServiceBaseUrl = walletServiceBaseUrl;
    }

    public void bootstrapWallet(UUID userId, Role role) {
        if (role != Role.BUYER) {
            return;
        }
        postTopUp(userId, DEFAULT_BUYER_STARTING_BALANCE);
    }

    public Optional<WalletBalanceSnapshot> getWalletBalance(UUID userId) {
        try {
            WalletBalanceResponse response = restTemplate.getForObject(
                walletServiceBaseUrl + "/wallets/" + userId + "/balance",
                WalletBalanceResponse.class
            );
            if (response == null) {
                return Optional.empty();
            }
            return Optional.of(new WalletBalanceSnapshot(response.availableBalance(), response.heldBalance()));
        } catch (HttpStatusCodeException | ResourceAccessException ex) {
            return Optional.empty();
        }
    }

    private void postTopUp(UUID userId, BigDecimal amount) {
        try {
            restTemplate.exchange(
                walletServiceBaseUrl + "/wallets/" + userId + "/top-up",
                HttpMethod.POST,
                new HttpEntity<>(new AmountPayload(amount)),
                Void.class
            );
        } catch (HttpStatusCodeException ex) {
            throw new ResponseStatusException(ex.getStatusCode(), ex.getResponseBodyAsString());
        } catch (ResourceAccessException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Wallet service unavailable");
        }
    }

    private record AmountPayload(BigDecimal amount) {
    }

    private record WalletBalanceResponse(
        UUID userId,
        BigDecimal availableBalance,
        BigDecimal heldBalance
    ) {
    }

    public record WalletBalanceSnapshot(
        BigDecimal availableBalance,
        BigDecimal heldBalance
    ) {
    }
}
