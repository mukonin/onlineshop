package com.onlineshop.dao.impl;

import com.onlineshop.dao.VerificationTokenDAO;
import com.onlineshop.model.VerificationToken;
import lombok.extern.log4j.Log4j;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;


@Log4j
@Repository
public class VerificationTokenDAOImpl extends GenericDAOImpl<VerificationToken, Long> implements VerificationTokenDAO {
	@Override
	public VerificationToken findByToken(String token) {
		try {
			log.info("find verifivation tolen by token: " + token);
			Query<VerificationToken> query = getSession().createQuery("from VerificationToken where token = :p_token");
			return query.setParameter("p_token", token).uniqueResult();
		} catch (Exception ex) {
			log.error("error find verifivation tolen by token: " + token);
			throw ex;
		}
	}
}
