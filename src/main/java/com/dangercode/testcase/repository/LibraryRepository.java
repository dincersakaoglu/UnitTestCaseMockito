package com.dangercode.testcase.repository;

import com.dangercode.testcase.entity.Library;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<Library, String> {
}
