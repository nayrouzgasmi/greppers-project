package com.example.taylor.Services;

import com.example.taylor.Repository.CodepromoRepository;
import com.example.taylor.Repository.PanierRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CodepromoService implements ICodepromoService{
    @Autowired
    CodepromoRepository codepromoRepository;
}
