package tn.esprit.pidev.Services;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.Entities.Refund;
import tn.esprit.pidev.Repositories.RefundRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class RefundService implements IRefundService {
    RefundRepository refundRepository;

    @Override
    public List<Refund> retrieveAllRefund() {
        return refundRepository.findAll();
    }

    @Override
    public Refund addRefund(Refund r) {
        refundRepository.save(r);
        return r;
    }

    @Override
    public Refund updateRefund(Refund r) {
        refundRepository.save(r);
        return r;
    }

    @Override
    public Refund retrieveRefund(Integer id) {
        return refundRepository.findById(id).get();
    }

    @Override
    public void deleteRefund(Integer id) {
        refundRepository.deleteById(id);

    }
}
