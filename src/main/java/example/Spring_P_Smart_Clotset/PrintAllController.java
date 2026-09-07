package example.Spring_P_Smart_Clotset;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrintAllController {
    
    @Autowired
    private PrintAllService printAllService;

    // 의류전체 조회
    @GetMapping("/smart_closet")
    public ArrayList<ClothesDto> clothesPrintAll(){
        return printAllService.clothesPrintAll();
    }
}
