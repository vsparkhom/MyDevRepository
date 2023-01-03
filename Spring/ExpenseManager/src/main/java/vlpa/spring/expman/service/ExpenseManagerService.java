package vlpa.spring.expman.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vlpa.spring.expman.dao.ExpenseDAO;

@Service
public class ExpenseManagerService {

    @Autowired
    private ExpenseDAO expenseDAO;

}
