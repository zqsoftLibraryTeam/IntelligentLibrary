package com.intelligentLibrary.dev.Dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.intelligentLibrary.dev.Dao.RuleBookDAO;
import com.intelligentLibrary.dev.base.BaseDAOImpl;
import com.intelligentLibrary.recommand.entity.RuleBook;

@Repository
@Transactional
public class RuleBookDAOImpl extends BaseDAOImpl<RuleBook> implements RuleBookDAO{

}
