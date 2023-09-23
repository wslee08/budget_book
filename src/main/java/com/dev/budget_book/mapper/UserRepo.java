package com.dev.budget_book.mapper;

import com.dev.budget_book.config.QueryDslConfig;
import com.dev.budget_book.model.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.dev.budget_book.model.QUsers.users;

@Repository
@RequiredArgsConstructor
public class UserRepo {
    private final QueryDslConfig qdc;
    private final PasswordEncoder passwordEncoder;

    public List<Users> allUsers() {
        QUsers user = users;
        List<Users> allUsers = qdc.query().select(user).from(user).fetch();
        return allUsers;
    }

    public FixedInOut getFixedInOut(String userId) {
        FixedInOut fixedInOut = new FixedInOut();
        QFixedIncome fixedIncome = QFixedIncome.fixedIncome;
        QFixedExpended fixedExpended = QFixedExpended.fixedExpended;
        QUsers user = users;

        fixedInOut.setFixedIncomes(qdc.query().select(fixedIncome).from(fixedIncome).innerJoin(user).on(user.email.eq(fixedIncome.userId)).where(user.email.eq(userId)).fetch());
        fixedInOut.setFixedExpendeds(qdc.query().select(fixedExpended).from(fixedExpended).innerJoin(user).on(user.email.eq(fixedExpended.userId)).where(user.email.eq(userId)).fetch());
        return fixedInOut;
    }

    public Users login(Users user) {
        Users loginUser = qdc.query().select(users).from(users).where(users.email.eq(user.getEmail())).fetchOne();
        if(passwordEncoder.matches(user.getPassword(), loginUser.getPassword())){
            user.setUsername("계정있슈");
            return user;
        } else {
            user.setUsername("계정없슈");
            return user;
        }
    }
    @Transactional
    public Users pwChange(Users user) {

        qdc.query().update(users).set(users.password, passwordEncoder.encode(user.getPassword())).where(users.email.eq(user.getEmail())).execute();

        Users rst = qdc.query().select(users).from(users).where(users.email.eq(user.getEmail())).fetchOne();
        return rst;
    }
}
