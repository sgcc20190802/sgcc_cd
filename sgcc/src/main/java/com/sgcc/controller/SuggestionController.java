package com.sgcc.controller;

import com.example.result.Result;
import com.sgcc.dto.*;
import com.sgcc.exception.TopErrorCode;
import com.sgcc.service.SuggestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "", tags = "意见建议接口")
@RestController
@RequestMapping(value = "/Suggestions")
@Controller
public class SuggestionController {
    @Autowired
    private SuggestionService suggestionService;

    @ApiOperation(value = "获取意见列表", notes = "")
    @GetMapping(value = "/users/{id}")
    public Result Suggestions(@PathVariable("id") String userId) {
        List<SuggestionViewDTO> dtos = suggestionService.getSuggestions(userId);

        if( dtos == null )
            return Result.failure( TopErrorCode.NO_DATAS);

        if( dtos.size() < 1)
            return Result.success( );

        return Result.success(dtos);
    }

    @ApiOperation(value = "提交意见", notes = "")
    @PostMapping(value = "/users/{id}")
    public Result submit(@RequestBody SuggestionSubmitDTO suggestionSubmitDTO, @PathVariable("id") String userId) {

        return suggestionService.submit(suggestionSubmitDTO,userId);
    }

    @ApiOperation(value = "查看意见", notes = "")
    @GetMapping(value = "/{id}")
    public Result Suggestion(@PathVariable("id") String suggestionId) {
        return suggestionService.getSuggestion(suggestionId);
    }

    @ApiOperation(value = "回复意见", notes = "")
    @PutMapping(value = "/{id}")
    public Result reply(@RequestBody SuggestionReplyDTO suggestionReplyDTO, @PathVariable("id") String suggestionId) {
        return suggestionService.reply(suggestionReplyDTO);
    }

    @ApiOperation(value = "批量删除意见", notes = "")
    @PostMapping(value = "/Deletes")
    public Result delete(@RequestBody SuggestionDeleteDTO dto ) {
        return suggestionService.delete(dto);
    }

    /**
     * 后台管理系统查询意见
     */
    @ApiOperation(value = "后台管理系统查询意见", notes = "")
    @GetMapping(value = "/Suggestions")
    public Result getSuggestions(  )
    {
        return suggestionService.getSuggestions();
    }
    /**
     * 后台管理系统修改意见
     */
    @ApiOperation(value = "后台管理系统修改意见", notes = "")
    @PutMapping(value = "/Suggestions/{id}")
    public Result updateSuggestion(SuggestionMappingDTO dto , @PathVariable("id") String suggestionId)
    {
        return suggestionService.update(dto);
    }
    /**
     * 后台管理系统新增意见
     */
    @ApiOperation(value = "后台管理系统新增意见", notes = "")
    @PostMapping(value = "/Suggestions/{id}")
    public Result AddSuggestion(SuggestionMappingDTO dto, @PathVariable("id") String suggestionId )
    {
        return suggestionService.AddSuggestion(dto);
    }
    /**
     * 后台管理系统删除意见
     */
    @ApiOperation(value = "后台管理系统删除意见", notes = "")
    @DeleteMapping(value = "/Suggestions/{id}}")
    public Result deletes(@RequestBody SuggestionDeleteDTO dto, @PathVariable("id") String suggestionId ) {
        return suggestionService.delete(dto);
    }
    /**
     * 后台管理系统批量删除意见
     */
    @ApiOperation(value = "后台管理系统批量删除意见", notes = "")
    @PostMapping(value = "/Suggestions/Deletes")
    public Result deletes(@RequestBody SuggestionDeleteDTO dto ) {
        return suggestionService.delete(dto);
    }
}
