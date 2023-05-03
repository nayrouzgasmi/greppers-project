package tn.esprit.pidev.Services;


import tn.esprit.pidev.Entities.Refund;

import java.util.List;

public interface IRefundService {

    List<Refund> retrieveAllRefund();

    Refund addRefund(Refund r );

    Refund updateRefund (Refund r);

    Refund retrieveRefund (Integer id);

    void deleteRefund ( Integer id);
}
