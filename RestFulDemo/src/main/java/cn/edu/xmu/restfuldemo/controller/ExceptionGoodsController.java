package cn.edu.xmu.restfuldemo.controller;

import cn.edu.xmu.restfuldemo.controller.vo.GoodsVo;
import cn.edu.xmu.restfuldemo.model.Goods;
import cn.edu.xmu.restfuldemo.service.GoodsService;
import cn.edu.xmu.restfuldemo.util.GoodsNameNullException;
import cn.edu.xmu.restfuldemo.util.ResponseCode;
import cn.edu.xmu.restfuldemo.util.ResponseUtil;
import cn.edu.xmu.restfuldemo.util.ReturnObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

/**
 * @author  RenjieZheng
 */
@RestController /*Restful的Controller对象*/
@RequestMapping(value = "/exception/goods", produces = "application/json;charset=UTF-8")
public class ExceptionGoodsController {
    private final Logger logger = LoggerFactory.getLogger(BindingResultGoodsController.class);

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private HttpServletResponse httpServletResponse;

    @PostMapping("")
    public Object createGood(@RequestBody GoodsVo goodsVo) {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        long nanoTimeBegin = LocalDateTime.now().getNano();
        long nanoTimeEnd = nanoTimeBegin;
        try{
            if (null==goodsVo||null == goodsVo.getName()){
                nanoTimeEnd = LocalDateTime.now().getNano();
                throw new GoodsNameNullException("商品名称不能为空");
            }
            Goods new_goods = goodsService.createGoods(goodsVo);
            httpServletResponse.setStatus(HttpServletResponse.SC_CREATED);
            return ResponseUtil.ok(new_goods);
        }catch(Exception e){
            System.out.println("BindingResule:"+(nanoTimeEnd-nanoTimeBegin));
            logger.info("商品名称不能为空");
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            ReturnObject retObj = new ReturnObject();
            retObj.setErrmsg("商品名称不能为空");
            retObj.setErrno(ResponseCode.OK);
            return retObj;
        }



    }
}
