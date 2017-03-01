package spittr.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import spittr.data.SpittleRepository;

@Controller
@RequestMapping("/spittles")
public class SpittleController {
    private final Logger logger = LogManager.getLogger(HomeController.class);


    private static final String MAX_LONG_AS_STRING = "9223372036854775807";

    private SpittleRepository spittleRepository;

    @Autowired
    public SpittleController(SpittleRepository spittleRepository) {
        logger.debug("--------------SpittleController Constructor");

        this.spittleRepository = spittleRepository;
    }

    //    @RequestMapping(method = RequestMethod.GET)
//    public List<Spittle> spittles(
//            @RequestParam(value = "max", defaultValue = MAX_LONG_AS_STRING) long max,
//            @RequestParam(value = "count", defaultValue = "20") int count) {
//        logger.debug("--------------spittles");
//        //  return the logical view name like the request path, than in this case spittles.jsp
//        //  and tha model key for the attribute is inferred from the type, in this case a list thane spittleList
//        return spittleRepository.findSpittles(max, count);
//    }

    @RequestMapping(method = RequestMethod.GET)
    public String spittles(Model model,
                           @RequestParam(value = "max", defaultValue = MAX_LONG_AS_STRING) long max,
                           @RequestParam(value = "count", defaultValue = "20") int count) {

        logger.debug("--------------SpittleController spittles");

        model.addAttribute("spittleList",
                spittleRepository.findSpittles(max, count));

        return "spittles";
    }

    //http://localhost:8080/spittles/499
    @RequestMapping(value = "/{spittleId}", method = RequestMethod.GET)
    public String spittle(
            @PathVariable("spittleId") long spittleId,
            Model model) {
        logger.debug("--------------spittle");

        model.addAttribute("spittle",
                spittleRepository.findOne(spittleId));
        return "spittle";
    }

    //http://localhost:8080/spittles/show?spittle_id=499
    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String showSpittle(
            @RequestParam("spittle_id") long spittleId,
            Model model) {
        model.addAttribute(spittleRepository.findOne(spittleId));
        return "spittle";
    }


//  @RequestMapping(method= RequestMethod.POST)
//  public String saveSpittle(SpittleForm form, Model model) throws Exception {
//    spittleRepository.save(new Spittle(null, form.getMessage(), new Date(),
//        form.getLongitude(), form.getLatitude()));
//    return "redirect:/spittles";
//  }

}