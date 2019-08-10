package com.example.ProjectAllianz.controller;

import com.example.ProjectAllianz.dto.QuoteDto;
import com.example.ProjectAllianz.model.Quote;
import com.example.ProjectAllianz.service.QuoteService;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/quote")

public class CustomerController {

    @Autowired
    private QuoteService quoteService;


    private ModelMapper modelMapper = new ModelMapper();



    /**
     * This method working about get information fund from databaseproject table is quote
     * @return
     */


    @ApiOperation(value = "Information Quote", notes = "Information Quote test")
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<QuoteDto> getAllCustomer(){
        List<Quote> quotes = quoteService.getAllCustomer();
        return quotes.stream().map(quote -> convertToDto(quote)).collect(Collectors.toList());
    }




    /**
     * frontend create client information and send to this method and save in database
     *
     */

//    @ApiOperation(value = "Create Quote", notes = "Create Quote Test")
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public QuoteDto createCustomer(@RequestBody QuoteDto quoteDto) throws ParseException {
        Quote quote = convertToEntity(quoteDto);
        Quote quoteCreate = quoteService.addCustomer(quote);
        return convertToDto(quote);
    }
    //
//
//    @ApiOperation(value = "Get Quote by id", notes = "Get Quote by id test")
//
//    @GetMapping(value = "/{id}")
//    @ResponseBody
//    public QuoteDto getCustomer(@PathVariable("id") int id){
//        return convertToDto(quoteService.getCustomerById(id));
//    }
//
//    @ApiOperation(value = "Put Quote", notes = "Put Quote test")
//
//    @PutMapping(value = "/{id}" )
//    @ResponseStatus(HttpStatus.OK)
//    public void updateCustomer(@RequestBody QuoteDto quoteDto) throws ParseException{
//        Quote quote = convertToEntity(quoteDto);
//
//    }





    /**
     * convert to Dto and convert to entity
     *
     */

    private QuoteDto convertToDto(Quote quote){
        QuoteDto quoteDto = modelMapper.map(quote, QuoteDto.class);

        return quoteDto;

    }


    /**
     * ยังใช้งานไม่ได้
     *
     */

    private Quote convertToEntity(QuoteDto quoteDto) throws ParseException {
        Quote quote = modelMapper.map(quoteDto, Quote.class);

        if ((quoteDto.getId()) != 0 ){
            Quote oldQuote = quoteService.getCustomerById(quoteDto.getId());
            System.out.println(quote.getId()+ " Quote");
            System.out.println(quoteDto.getId() + "New Quote");
            quote.setId(oldQuote.getId());

        }
        return quote;
    }




//    @Autowired
//    private QuoteRepository customerRepository;
//
//    @ApiOperation(value = "Information Quote", notes = "Information Quote test")
//    @GetMapping(value = "/all")
//    public List<Quote> customers()
//    {
//        return customerRepository.findAll();
//    }
//
////    @GetMapping(value = "/all")
////    public List<Quote> getAll(){
////        return customerRepository.findAll();
////    }
////
//    @PostMapping(value = "/create"  )
//
//    public List<Quote> createCustomer (@RequestBody Quote customer){
//        customerRepository.save(customer);
//        return customerRepository.findAll();
//    }
//
//
//    /**
//     *
//     */
//
//
//
//    @Autowired
//    private FundRepository fundRepository;
//
//    @GetMapping(value="/fund/all")
//    public List<FundCustomer> getAll(){
//        return fundRepository.findAll();
//
//    }
//
//    @PostMapping(value = "/funds/create")
//    public List<FundCustomer> createFund (@RequestBody FundCustomer funds){
//        fundRepository.save(funds);
//        return fundRepository.findAll();
//
//    }

}
