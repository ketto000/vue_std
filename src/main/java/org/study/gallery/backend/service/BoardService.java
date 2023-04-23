package org.study.gallery.backend.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.study.gallery.backend.entity.Item;
import org.study.gallery.backend.repository.BoardRepository;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class BoardService
{
    @Autowired
    private BoardRepository boardRepository;

    //글작성
    public void write(Item item_list, MultipartFile file) throws Exception{

        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
        //경로
        UUID uuid = UUID.randomUUID();

        String fileName = uuid + "_" + "img_" + file.getOriginalFilename();
        //랜덤파일이름 생성
        File saveFile = new File(projectPath, fileName);
        file.transferTo(saveFile);
        item_list.setName(fileName);
        item_list.setImgPath("/files/" + fileName );

        boardRepository.save(item_list);
    }

    //게시글 리스트
//    public List<card_list> board_list(){
     public Page<Item> board_list(Pageable pageable){
        return boardRepository.findAll(pageable);
    }

    //검색 게시글
    public Page<Item> boardSearchList(String searchKeyword, Pageable pageable){
        return boardRepository.findByNameContaining(searchKeyword,pageable);
    }

    //특정 게시글 불러오기
    public Item boardView(Integer id){
        return boardRepository.findById(id).get();
    }

    public void boardDelete(Integer id){
        boardRepository.deleteById(id);
    }




}