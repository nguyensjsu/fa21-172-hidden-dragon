package com.example.hiddendragon;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DSRepository extends JpaRepository<DSCommand, Long>{
    
}
