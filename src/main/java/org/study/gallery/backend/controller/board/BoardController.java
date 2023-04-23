package org.study.gallery.backend.controller.board;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.study.gallery.backend.entity.Item;
import org.study.gallery.backend.repository.ItemRepository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.relational.core.sql.In;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.study.gallery.backend.service.BoardService;


import java.util.List;

@RestController
//@CrossOrigin(origins = "http://192.168.1.7:3001/")  // 해당 출처 허용
public class BoardController {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    private BoardService boardService;


    @GetMapping("/api/board/list")

    public ResponseEntity getItems(Model model ,
                                @PageableDefault(page = 0,size = 5, sort = "id" ,direction = Sort.Direction.DESC) Pageable pageable,
                                String searchKeyword) {

//        List<Item> items = itemRepository.findAll();
//            System.out.println("실행");
        System.out.println( "---->" + pageable.getPageNumber());
        Page<Item> list = null;
        if(searchKeyword == null ){
            list = boardService.board_list(pageable);
        }else{
            list = boardService.boardSearchList(searchKeyword,pageable);
        }

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4 ,1);
        int endPage = Math.min(nowPage + 5 , list.getTotalPages());

        model.addAttribute("total" ,list.getTotalElements()); //총게시글수
////        Math.round()
////        Math.ceil()
////        Math.floor()


//        int cnt = list.getTotalElements();//게시판의 총 레코드 갯수


        model.addAttribute("list" ,list); //model view control
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage",endPage);

//        return new ResponseEntity<>(list,HttpStatus.OK);

//        return "list";
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/api/board/list2")
    public String getList() {
        return "hello world";
    }


    @GetMapping("/")
    @ResponseBody
    public String main(){
        return "hello";
    }


    @GetMapping("/board/list")
    public String boardList(Model model ,
                            @RequestParam(value = "page", defaultValue = "1") Integer page,
                            @PageableDefault(page = 0,size = 10, sort = "cardnum" ,direction = Sort.Direction.DESC) Pageable pageable,
                            String searchKeyword){




        Page<Item> list = null;
        if(searchKeyword == null ){
            list = boardService.board_list(pageable);
        }else{
            list = boardService.boardSearchList(searchKeyword,pageable);
        }




        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4 ,1);
        int endPage = Math.min(nowPage + 5 , list.getTotalPages());

        model.addAttribute("total" ,list.getTotalElements()); //총게시글수
//        Math.round()
//        Math.ceil()
//        Math.floor()
        model.addAttribute("list" ,list); //model view control
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage",endPage);





        return "boardlist";


    }



    @GetMapping("/board/write")
    public String boardWriteForm(){
        return "boardwrite";
    }

    @PostMapping("/board/writepro")
    public String boardWritePro(Item card_list, Model model, MultipartFile imgfile) throws Exception{

        boardService.write(card_list,imgfile) ;


        model.addAttribute("message","글 작성이 완료되었습니다.");
        model.addAttribute("searchUrl","/board/list");

        //return "message";
        return "redirect:/board/list";
    }

    @GetMapping("/board/view") //localhost:8088/board/view?id=1
    public String boardView(Model model, Integer id, Integer Page){

        model.addAttribute("board",boardService.boardView(id));
        model.addAttribute("getPage",Page-1);
        return "boardview";
    }

    @GetMapping("/board/delete")
    public String boardDelete(Integer id){
        boardService.boardDelete(id);
        return "redirect:/board/list";
    }

    @GetMapping("/board/modify/{id}")
    public String boardModify(@PathVariable("id") Integer id, Model model){


        model.addAttribute("board",boardService.boardView(id));
        return "boardmodify";
    }

    @PostMapping("/board/update/{id}")
    public String boardUpdate(@PathVariable("id") Integer id,Item item_list, Model model, MultipartFile imgfile)  throws Exception{
        Item item_temp= boardService.boardView(id);
        item_temp.setName(item_list.getName());
        item_temp.setImgPath(item_list.getImgPath());
        boardService.write(item_temp,imgfile);
        model.addAttribute("board",boardService.boardView(id));
        return "boardview";

    }
}



