package com.kinder.kindergarten.controller;

import com.kinder.kindergarten.DTO.MaterialFormDTO;
import com.kinder.kindergarten.DTO.MaterialSearchDTO;
import com.kinder.kindergarten.entity.MaterialEntity;
import com.kinder.kindergarten.service.MaterialService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/material")
public class MaterialController {

    private final MaterialService materialService;

    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @GetMapping(value = "/new")
    public String materialForm(Model model){
        model.addAttribute("materialFormDTO", new MaterialFormDTO());

        return "material/materialForm"; // materialForm.html의 경로에 보냄

    }

    // 상품 등록
    @PostMapping(value = "/new")
    public String materialNew(@Valid MaterialFormDTO materialFormDTO, BindingResult bindingResult,
                          Model model, @RequestParam("materialImgFile") List<MultipartFile> materialImgFileList){

        if(bindingResult.hasErrors()){
            return "material/materialForm";

            // itemForm.html 에 에러 보냄
            // $(document).ready(function(){
            //            var errorMessage = [[${errorMessage}]];
            //            if(errorMessage != null){
            //                alert(errorMessage);
            //            }
            //
            //            bindDomEvent();
            //        //  정상일 때 22행 함수 실행
            //
            //        });
        }

        if(materialImgFileList.get(0).isEmpty() && materialFormDTO.getId() == null){
            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력 값 입니다.");
            return "material/materialForm";
        }

        try {
           materialService.saveMaterial(materialFormDTO, materialImgFileList);
        } catch (Exception e){
            model.addAttribute("errorMessage", "상품 등록 중 에러가 발생하였습니다.");
            return "material/materialForm";
        }

        return "redirect:/";
    }

    // 자재 수정 관련
    @GetMapping(value = "/{id}")
    public String materialDtl(@PathVariable("id") Long id, Model model){

        try {
            MaterialFormDTO materialFormDTO = materialService.getMaterialDtl(id);
            model.addAttribute("materialFormDTO", materialFormDTO);
        } catch(EntityNotFoundException e){
            model.addAttribute("errorMessage", "존재하지 않는 자재 입니다.");
            model.addAttribute("materialFormDTO", new MaterialFormDTO());
            return "material/materialForm";
        }

        return "material/materialForm";
    }

    @PostMapping(value = "/{id}")
    public String materialUpdate(@Valid MaterialFormDTO materialFormDTO, BindingResult bindingResult,
                             @RequestParam("materialImgFile") List<MultipartFile> materialImgFileList, Model model){
        if(bindingResult.hasErrors()){
            return "material/materialForm";
        }

        if(materialImgFileList.get(0).isEmpty() && materialFormDTO.getId() == null){
            model.addAttribute("errorMessage", "첫번째 자재 이미지는 필수 입력 값 입니다.");
            return "material/materialForm";
        }

        try {
            materialService.updateMaterial(materialFormDTO, materialImgFileList);
        } catch (Exception e){
            model.addAttribute("errorMessage", "자재 수정 중 에러가 발생하였습니다.");
            return "material/materialForm";
        }

        return "redirect:/";
    }

    @GetMapping(value = {"/materials", "/materials/{page}"})  //페이징이 없는경우, 있는 경우
    public String materialManage(MaterialSearchDTO materialSearchDTO, @PathVariable("page") Optional<Integer> page, Model model){

        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 3);
        // 페이지 파라미터가 없으면 0번 페이지를 보임. 한 페이지당 3개의 상품만 보여줌.
        Page<MaterialEntity> materials = materialService.getMaterialPage(materialSearchDTO, pageable);  // 조회 조건, 페이징 정보를 파라미터로 넘겨서 Page 타입으로 받음
        // 조회 조건과 페이징 정보를 파라미터로 넘겨서 item 객체 받음
        model.addAttribute("materials", materials); // 조회한 상품 데이터 및 페이징정보를 뷰로 전달
        model.addAttribute("materialSearchDTO", materialSearchDTO); // 페이지 전환시 기존 검색 조건을 유지
        model.addAttribute("maxPage", 5);   // 상품관리 메뉴 하단에 보여줄 페이지 번호의 최대 개수 5

        return "material/materialMng";
        // materialMng.html로 리턴함.
    }

    @GetMapping(value = "/materialDtl/{id}") // 자재 상세 페이지
    public String itemDtl(Model model, @PathVariable("id") Long id){
        MaterialFormDTO materialFormDTO = materialService.getMaterialDtl(id);
        model.addAttribute("material", materialFormDTO);
        return "material/materialDtl";
    }

    @PostMapping("/delete/{id}")
    public String deleteMaterial(@PathVariable("id") Long id) {
        try {
            materialService.deleteMaterial(id);
        } catch (Exception e) {
            // 에러 처리
        }
        return "redirect:/material/materials";
    }

}
