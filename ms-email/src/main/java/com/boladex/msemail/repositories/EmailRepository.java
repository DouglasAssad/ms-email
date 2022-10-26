package com.boladex.msemail.repositories;

import com.boladex.msemail.models.EmailModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<EmailModel, Long> {
}
