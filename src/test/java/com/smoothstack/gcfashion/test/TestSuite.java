package com.smoothstack.gcfashion.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ UpdateTransaction.class, Read.class, SaveTransaction.class, Find.class, Payment.class })
public class TestSuite {

}
