package example.day45_day07_0909;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController @RequestMapping ("/day07/todo")
public class TodoController {
    @Autowired 
    private TodoRepository todoRepository;
/* 
    @GetMapping("") // TodoEntity findByTitle( String title );
    public TodoEntity findByTitle(@RequestParam(name = "title") String title) {
        return todoRepository.findByTitle(title);
    }
*/
    // List<TodoEntity> findByTitleAndContent( String title, String content );
    @GetMapping("")
    public List<TodoEntity> findByTitleAndContent(@RequestParam(name = "title") String title, @RequestParam(name = "content") String content) {
        return todoRepository.findByTitleAndContent(title, content);
    }
    
    // Map<String,Object> findByTitleOrContent( String title, String content );
    
    
}
