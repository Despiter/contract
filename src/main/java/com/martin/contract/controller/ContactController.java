package com.martin.contract.controller;

import com.martin.contract.persist.entity.ContactPo;
import com.martin.contract.persist.mapper.ContactMapper;
import com.martin.contract.persist.repo.ContactRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/contact")
public class ContactController extends BaseController<ContactPo, ContactMapper, ContactRepo> {
    public ContactController(ContactRepo repo) {
        super(repo);
    }
}