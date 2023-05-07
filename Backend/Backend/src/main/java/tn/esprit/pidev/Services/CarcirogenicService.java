package tn.esprit.pidev.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.pidev.Repositories.CarcirogenicRepository;

@Service
public class CarcirogenicService {
    @Autowired
    CarcirogenicRepository carcirogenicRepository;
    
}
