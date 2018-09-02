package com.runnerpackage;

import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;



@CucumberOptions
(
features="Feature/FlightBooking.feature", 
glue="com.stepDefinition",
plugin = {"pretty:STDOUT","html:target/HTMLCucumberReport","json:target/Cucumber.json"}

)

public class RunnerClass extends AbstractTestNGCucumberTests
{

	@Test
	public void t()
	{}
}
