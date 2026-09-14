package example.totalpractice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import example.totalpractice.model.dto.ProductDto;
import example.totalpractice.model.entity.ProductEntity;
import example.totalpractice.service.ProductService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@CrossOrigin (value = "http://localhost:5173")
@RestController @RequestMapping ("/api/products")
public class ProductController {
    @Autowired 
    private ProductService productService;

    // 수정
    @PutMapping("")
    public boolean update(@RequestBody ProductDto productDto) {    
        return productService.update(productDto);
    }

    // 삭제
    @DeleteMapping ("")
    public boolean delete(@RequestParam(name = "bno") Integer bno){
        return productService.delete(bno);
    }
    
    // 저장
    @PostMapping ("")
    public boolean 제품등록(@RequestBody ProductDto productDto){
        return productService.제품등록(productDto);
    }

    // 전체 조회
    @GetMapping ("")
    public List<ProductDto> 제품전체조회(){
        return productService.제품전체조회();
    }

}
