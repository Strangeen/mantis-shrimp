package online.dinghuiye.mantisshrimp.bing.controller;

import online.dinghuiye.bingcollection.consts.BingParam;
import online.dinghuiye.bingcollection.entity.BingImageFile;
import online.dinghuiye.bingcollection.pojo.BingItemEntity;
import online.dinghuiye.bingcollection.service.BingItemService;
import online.dinghuiye.bingcollection.service.impl.Access;
import online.dinghuiye.mantisshrimp.consts.MsParam;
import online.dinghuiye.mantisshrimp.entity.BingItemInfo;
import online.dinghuiye.mantisshrimp.entity.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author Strangeen on 2018/02/20
 */
@Controller
@RequestMapping(value = "/bing", method = RequestMethod.GET)
public class PhotoController {

    @Autowired
    private Access bingAllSaveAccessService;

    @Autowired
    private BingItemService itemService;

    @RequestMapping("")
    public String index(HttpServletRequest request, BingItemInfo queryItem, PageInfo pageInfo, Model model) {

        int page = 0;
        try {
            page = Integer.valueOf(pageInfo.getPage()) - 1;
        } catch (Exception e) {
            // do nothing
        }
        int size = MsParam.page_size;
        try {
            int size0 = pageInfo.getSize();
            if (size0 > 0) size = size0;
        } catch (Exception e) {
            // do nothing
        }

        BingItemEntity queryParam = new BingItemEntity()
                .setbTitle(queryItem.getTitle())
                .setbDate(queryItem.getDate());
        Page<BingItemEntity> itemPage = itemService.findAll(queryParam, new PageRequest(page, size, new Sort(new Sort.Order(Sort.Direction.DESC, "bDate"))));
        int colNum = BingParam.bing_photo_list_col_num;
        int rowNum = itemPage.getNumberOfElements() % colNum == 0 ? itemPage.getNumberOfElements() / colNum : itemPage.getNumberOfElements() / colNum + 1;

        model.addAttribute("itemPage", itemPage)
                .addAttribute("dateFormat", MsParam.date_format)
                .addAttribute("rowNum", rowNum)
                .addAttribute("colNum", colNum)
                .addAttribute("queryItem", queryItem);

        return "ms/bing/photo/photo";
    }

    /**
     * @param dateStr yyyyMMdd
     */
    @RequestMapping(value = "/create.ms", method = RequestMethod.POST)
    public String create(@RequestParam("date") String dateStr) {

        try {
            SimpleDateFormat bingSdf = new SimpleDateFormat(BingParam.bing_date_format);
            Date date = new Date();
            if (!StringUtils.isBlank(dateStr)) {
                date = bingSdf.parse(dateStr);
            }
            bingAllSaveAccessService.create(
                    date, // 输入的日期也按照yyyyMMdd格式
                    new BingImageFile(
                        BingParam.bing_img_save_path,
                        BingParam.bing_img_folder_format,
                        BingParam.bing_img_prefix + "-" +
                                bingSdf.format(date) + "-" +
                                UUID.randomUUID() + ".jpg"),
                    BingParam.bing_small_img_width, true);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return "redirect:/bing";
    }

    @RequestMapping(value = "/doDelete.ms")
    public String doDelete(@RequestParam("id") String idStr) {

        Long id = Long.valueOf(idStr);
        BingItemEntity item = itemService.findById(id);
        deleteImgFile(item.getImgLocalUrl());
        deleteImgFile(item.getSmallImgUrl());
        itemService.delete(id);

        return "redirect:/bing";
    }

    private void deleteImgFile(String imgUrl) {
        String rootPath = BingParam.bing_img_save_path;
        if (rootPath.endsWith("/")) rootPath = rootPath.substring(0, rootPath.length() - 1);
        File img = new File(rootPath + imgUrl);
        if (img.exists()) img.delete();
    }

//    @RequestMapping(value = "/detal.ms")
//    public String detail
}
