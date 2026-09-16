package org.backend.controller;

import org.backend.model.Partner;
import org.backend.repository.PartnerRepository;
import org.backend.service.impl.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/distribution")
@CrossOrigin("*")
public class PartnerController {
    @Autowired
    private PartnerRepository partnerRepo;
    @Autowired
    private EmailService emailService;

    @GetMapping
    public List<Partner> getAllPartners() {
        return partnerRepo.findAll();
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Partner partner) {
        // 1. Lưu vào Database
        Partner savedPartner = partnerRepo.save(partner);

        // 2. Gửi Email thông báo cho Huy
        String emailBody = "Bạn có yêu cầu hợp tác mới!\n\n" +
                "Khách hàng: " + partner.getName() + "\n" +
                "SĐT: " + partner.getPhone() + "\n" +
                "Địa chỉ: " + partner.getAddress() + "\n" +
                "Nội dung: " + partner.getContent();

        emailService.sendEmail("tranquanghuyit101@gmail.com", "🔥 YÊU CẦU HỢP TÁC MỚI", emailBody);

        return ResponseEntity.ok("Thành công");
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestBody String newStatus) {
        return partnerRepo.findById(id).map(contact -> {
            contact.setStatus(newStatus);
            partnerRepo.save(contact);
            return ResponseEntity.ok("Đã cập nhật trạng thái!");
        }).orElse(ResponseEntity.notFound().build());
    }
}
