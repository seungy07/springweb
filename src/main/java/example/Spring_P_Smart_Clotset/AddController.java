package example.Spring_P_Smart_Clotset;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddController {
    @Autowired private AddService addService;

    // 의류 등록(C)
    @PostMapping("/smart_closet")
    public boolean clothesAdd(@RequestBody ClothesDto closetDto, @RequestParam(name = "mno")int mno){
        return addService.clothesAdd(closetDto, mno);
    }
}
