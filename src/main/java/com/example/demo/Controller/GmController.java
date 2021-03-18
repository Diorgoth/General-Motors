package com.example.demo.Controller;

import com.example.demo.Entity.GM;
import com.example.demo.Payload.Gmdto;
import com.example.demo.Repository.GmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class GmController {

    @Autowired
    GmRepository gmRepository;

    @RequestMapping(value = "/generalmotors", method = RequestMethod.GET)
    public List<GM> getGM() {

        List<GM> GMList = gmRepository.findAll();

        return GMList;

    }

    @RequestMapping(value = "/generalmotors/{id}",method = RequestMethod.GET)
    public GM getGM(@PathVariable Integer id) {

        Optional<GM> optionalGM = gmRepository.findById(id);

        if (optionalGM.isPresent()){

            GM gm = optionalGM.get();
            return gm;

        }else {

            return new GM();
        }

    }


    @RequestMapping(value = "/generalmotors", method = RequestMethod.POST)
    public String addGM(@RequestBody Gmdto gmdto) {

      GM gm = new GM();

      gm.setCorpName(gmdto.getCorpname());
      gm.setAddress(gmdto.getAddress());
      gm.setDirectorName(gmdto.getDirectorname());
      gmRepository.save(gm);

      return "GM added";
    }



    @RequestMapping(value = "/generalmotors/{id}", method = RequestMethod.PUT)
    public String editGM(@PathVariable Integer id, @RequestBody Gmdto gmdto) {

        Optional<GM> optionalGM = gmRepository.findById(id);

        if (optionalGM.isPresent()){

            GM gm = optionalGM.get();;

            gm.setCorpName(gmdto.getCorpname());
            gm.setAddress(gmdto.getAddress());
            gm.setDirectorName(gmdto.getDirectorname());

            gmRepository.save(gm);
            return "GM edited";

        }else if (!optionalGM.isPresent()){

            return "GM not found";

        }

      return "";

    }

    @RequestMapping(value = "/generalmotors/{id}", method = RequestMethod.DELETE)
    public String deleteGM(@PathVariable Integer id) {

      gmRepository.deleteById(id);

        return "GM deleted";

    }



}
