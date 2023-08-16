package br.com.henrique.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.henrique.converters.NumberConverter;
import br.com.henrique.exceptions.UnsupportedMathOperationException;
import br.com.henrique.math.SimpleMath;

@RestController
public class MathController {

	private SimpleMath math = new SimpleMath();
	
	@RequestMapping(value = "/sum/{numberOne}/{numberTwo}",
			method=RequestMethod.GET)
	public Double sum(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo)
	{
		if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) 
			{
			throw new UnsupportedMathOperationException("Please set a numeric value");
			}
		return math.sum(NumberConverter.convertToDouble(numberOne),  NumberConverter.convertToDouble (numberTwo));
	}
	
	@RequestMapping(value = "/sub/{numberOne}/{numberTwo}",
			method=RequestMethod.GET)
	public Double sub(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo)
	{
		if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) 
		{
			throw new UnsupportedMathOperationException("Please set a numeric value");
		}
		return math.sub(NumberConverter.convertToDouble(numberOne),  NumberConverter.convertToDouble (numberTwo));
	}

	@RequestMapping(value = "/div/{numberOne}/{numberTwo}",
			method=RequestMethod.GET)
	public Double div(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo)
	{
		if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) 
		{
			throw new UnsupportedMathOperationException("Please set a numeric value");
		}
		return math.div(NumberConverter.convertToDouble(numberOne),  NumberConverter.convertToDouble (numberTwo));
	}

	@RequestMapping(value = "/mult/{numberOne}/{numberTwo}",
			method=RequestMethod.GET)
	public Double mult(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo)
	{
		if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) 
		{
			throw new UnsupportedMathOperationException("Please set a numeric value");
		}
		return math.mult(NumberConverter.convertToDouble(numberOne),  NumberConverter.convertToDouble (numberTwo));
	}

	@RequestMapping(value = "/med/{numberOne}/{numberTwo}",
			method=RequestMethod.GET)
	public Double med(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo)
	{
		if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) 
		{
			throw new UnsupportedMathOperationException("Please set a numeric value");
		}
		return math.med(NumberConverter.convertToDouble(numberOne),  NumberConverter.convertToDouble (numberTwo));
	}

	@RequestMapping(value = "/sqrt/{number}",
			method=RequestMethod.GET)
	public Double sqrt(
			@PathVariable(value = "number") String number)
	{
		if (!NumberConverter.isNumeric(number)) 
		{
			throw new UnsupportedMathOperationException("Please set a numeric value");
		}
		return math.sqrt(NumberConverter.convertToDouble(number));
	}

	
	
}
