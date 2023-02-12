package antifraud.transaction;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TransactionController {
    private Map<String, String> result;
    private HttpStatus status;
    @PostMapping("/api/antifraud/transaction")
    public ResponseEntity<Map<String, String>> postTransaction (@RequestBody Map<String, Long> transaction) {
        try {
            if ((transaction.get("amount") <= 0)) {
                status = HttpStatus.BAD_REQUEST;
                return new ResponseEntity<>(status);
            } else if (transaction.get("amount") <= 200) {
                result = Map.of("result", "ALLOWED");
                status = HttpStatus.OK;
            } else if (transaction.get("amount") <= 1500) {
                result = Map.of("result", "MANUAL_PROCESSING");
                status = HttpStatus.OK;
            } else {
                result = Map.of("result", "PROHIBITED");
                status = HttpStatus.OK;
            }
            return new ResponseEntity<>(result, status);
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(status);
        }
    }
}