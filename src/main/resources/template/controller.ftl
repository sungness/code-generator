package com.msymobile.urp.web.manage.business;

import com.msymobile.core.manage.annotation.Command;
import com.msymobile.core.manage.annotation.Module;
import com.msymobile.core.model.CommandResult;
import com.msymobile.core.model.enu.TaskEnum;
import com.msymobile.core.service.ServiceProcessException;
import com.msymobile.core.util.tools.LongTools;
import com.msymobile.core.web.query.QueryFilter;
import com.msymobile.urp.model${modulePkg}.${table.upperCaseName};
import com.msymobile.urp.model.enu.options.ListLimitEnum;
import com.msymobile.urp.model.enu.options${modulePkg}.${table.upperCaseName}OrderingEnum;
import com.msymobile.urp.service${modulePkg}.${table.upperCaseName}Service;
import com.msymobile.urp.validator${modulePkg}.${table.upperCaseName}Validator;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * ${table.clearComment}管理控制器类。
 * Created by wanghongwei on ${genDate}.
 */
@Module(value = ${table.upperCaseName}Controller.MODULE_NAME + "管理", order = 3)
@Controller
@RequestMapping(${table.upperCaseName}Controller.URL_PREFIX)
public class ${table.upperCaseName}Controller {
    private static final Logger log = LoggerFactory.getLogger(${table.upperCaseName}Controller.class);

    public static final String MODULE_NAME = "${table.clearComment}";
    public static final String URL_PREFIX = "/manage/${viewPath}";
    private static final String LIST_URL = "/list";
    private static final String DETAIL_URL = "/detail";
    private static final String EDIT_URL = "/edit";

    @Autowired
    private ${table.upperCaseName}Service ${table.camelCaseName}Service;

    @Autowired
    private ${table.upperCaseName}Validator ${table.camelCaseName}Validator;

    /**
     * 列表页处理方法
     * @param queryFilter QueryFilter 查询条件过滤器
     * @param result BindingResult
     * @param model Model 携带操作结果对象的视图模型对象
     * @param request HttpServletRequest
     * @throws ServletException 业务处理异常
     */
    @Command(value = MODULE_NAME + "列表", isInlet = true)
    @RequestMapping(LIST_URL)
    public void list(@ModelAttribute("queryFilter") QueryFilter queryFilter,
                     BindingResult result, Model model,
                     HttpServletRequest request)
            throws ServletException {
        queryFilter.init(request);
        if (${table.upperCaseName}OrderingEnum.enumOfValue(queryFilter.getOrdering()) == null) {
            queryFilter.setOrdering(${table.upperCaseName}OrderingEnum.getDefaultEnum().getValue());
        }
        try {
            List<${table.upperCaseName}> ${table.camelCaseName}List =
                    ${table.camelCaseName}Service.getList(queryFilter);
            model.addAttribute("${table.camelCaseName}List", ${table.camelCaseName}List);
        } catch (Exception e) {
            result.reject(e.getClass().getName(), e.getMessage());
            e.printStackTrace();
        }
        if (result.hasErrors()) {
            CommandResult<Integer> commandResult = new CommandResult<>(result.getObjectName());
            commandResult.addAllErrors(result);
            model.addAttribute("commandResult", commandResult);
        }
        model.addAttribute("orderingEnum", ${table.upperCaseName}OrderingEnum.values());
        model.addAttribute("limitEnum", ListLimitEnum.values());
        queryFilter.setForWeb();
    }

    /**
    * 编辑处理方法，GET方式请求直接返回编辑页
    * @param id Long 记录id
    * @param model Model
    * @param backURL String 返回的url地址
    */
    @Command(value = "查看" + MODULE_NAME)
    @RequestMapping(DETAIL_URL)
    public void detail(@RequestParam(required=false) Long id,
                       @RequestParam(required=false) String backURL, Model model) {
        ${table.upperCaseName} ${table.camelCaseName} = ${table.camelCaseName}Service.get(id);
        if (${table.camelCaseName}.getId() == null) {
            CommandResult<Integer> commandResult = new CommandResult<>("");
            commandResult.setSuccess(false);
            commandResult.setTitle("JGLOBAL_WARN");
            commandResult.setMessage("JGLOBAL_NO_MATCHED");
        }
        model.addAttribute("${table.camelCaseName}", ${table.camelCaseName});
        model.addAttribute("backURL", backURL);
    }

    /**
     * 编辑处理方法，GET方式请求直接返回编辑页
     * @param id Long 记录id
     * @param model Model
     * @param backURL String 返回的url地址
     */
    @Command(value = "编辑" + MODULE_NAME, showInMenu = true, alias= "增加" + MODULE_NAME)
    @RequestMapping(EDIT_URL)
    public void editGet(@RequestParam(required=false) Long id,
                        @RequestParam(required=false) String backURL, Model model) {
        if (!model.containsAttribute("${table.camelCaseName}")) {
            model.addAttribute("${table.camelCaseName}", ${table.camelCaseName}Service.getSafety(id));
        }
        model.addAttribute("backURL", backURL);
    }

    /**
     * 编辑处理方法，POST方式提交处理表单
     * @param ${table.camelCaseName} ${table.upperCaseName} ${table.clearComment}对象
     * @param result BindingResult 表单对象验证结果
     * @return CommandResult<Integer> 操作结果对象
     * @throws ServletException
     */
    @Command("保存" + MODULE_NAME)
    @RequestMapping(value="/save")
    public String save(${table.upperCaseName} ${table.camelCaseName}, BindingResult result,
                       @RequestParam(required=false) String task,
                       @RequestParam(required=false) String backURL,
                       RedirectAttributes model) throws ServletException {
        ${table.camelCaseName}Validator.validate(${table.camelCaseName}, result);
        CommandResult<Integer> commandResult = new CommandResult<>(result.getObjectName());
        if (!result.hasErrors()) {
            try {
                if (LongTools.lessEqualZero(${table.camelCaseName}.getId())) {
                    commandResult.setRes(${table.camelCaseName}Service.insert(${table.camelCaseName}));
                    commandResult.setMessage(MODULE_NAME + "信息已添加");
                } else {
                    commandResult.setRes(${table.camelCaseName}Service.update(${table.camelCaseName}));
                    commandResult.setMessage(MODULE_NAME + "信息已保存");
                }
            } catch (ServiceProcessException spe) {
                result.rejectValue(spe.getField(), spe.getErrorCode(), spe.getDefaultMessage());
                spe.printStackTrace();
            } catch (Exception e) {
                result.reject(e.getClass().getName(), e.getMessage());
                e.printStackTrace();
            }
        }

        commandResult.addAllErrors(result);
        StringBuilder redirectStb = new StringBuilder("redirect:");
        redirectStb.append(URL_PREFIX);
        if (commandResult.isSuccess()) {
            switch (TaskEnum.valueOfCode(task)) {
                case SAVE://保存 & 关闭-返回列表页
                    redirectStb.append(LIST_URL);
                    break;
                case SAVE2NEW://保存 & 新增-返回空白编辑页
                    redirectStb.append(EDIT_URL);
                    break;
                default://保存-返回当前记录编辑页，apply 及其他
                    model.addFlashAttribute("${table.camelCaseName}", ${table.camelCaseName});
                    redirectStb.append(EDIT_URL);
                    redirectStb.append("?id=").append(${table.camelCaseName}.getId());
                    break;
            }
        } else {
            model.addFlashAttribute("${table.camelCaseName}", ${table.camelCaseName});
            redirectStb.append(EDIT_URL);
            if (LongTools.greaterThanZero(${table.camelCaseName}.getId())) {
                redirectStb.append("?id=").append(${table.camelCaseName}.getId());
            }
        }
        model.addFlashAttribute("commandResult", commandResult);
        return redirectStb.toString();
    }

    /**
     * 删除记录
     * @param id String 记录id字符串，多个id以逗号分隔
     * @return CommandResult<Integer> 操作结果对象
     */
    @Command("删除" + MODULE_NAME)
    @RequestMapping("/delete")
    public String delete(@RequestParam(required=true) String id,
                         @RequestParam(required=false) String backURL,
                         RedirectAttributes model) {
        CommandResult<Integer> commandResult = new CommandResult<>("");
        try {
            int res = ${table.camelCaseName}Service.batchDelete(LongTools.parseList(id));
            if (res > 0) {
                commandResult.setSuccess(true);
                commandResult.setMessage("选中的记录已成功删除。");
                commandResult.setRes(res);
            } else {
                commandResult.reject("JGLOBAL_NO_MATCHED", "没有匹配的记录");
            }
        } catch (ServiceProcessException spe) {
            spe.printStackTrace();
            commandResult.reject(spe.getErrorCode(), spe.getDefaultMessage());
        } catch (Exception e) {
            e.printStackTrace();
            commandResult.reject(e.getClass().getName(), e.getMessage());
        }
        model.addFlashAttribute("commandResult", commandResult);
        if (StringUtils.isNotBlank(backURL)) {
            return backURL;
        } else {
            return String.format("redirect:%s%s", URL_PREFIX, LIST_URL);
        }
    }
}
