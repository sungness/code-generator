package ${packageBase}.controller${modulePkg};

import com.sungness.core.service.Pagination;
import com.sungness.core.service.ServiceProcessException;
import com.sungness.core.util.tools.LongTools;
import com.sungness.framework.web.support.annotation.Command;
import com.sungness.framework.web.support.annotation.Module;
import com.sungness.manage.component.controller.ManageControllerInterface;
import ${packageBase}.model${modulePkg}.${table.upperCaseName};
import com.sungness.manage.component.service${modulePkg}.${table.upperCaseName}Service;
import com.sungness.manage.ngx.component.controller.errors.BadRequestAlertException;
import com.sungness.manage.ngx.support.query.Query;
import com.sungness.manage.ngx.support.util.HeaderUtil;
import com.sungness.manage.ngx.support.util.PaginationUtil;
import com.sungness.manage.ngx.support.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;


/**
 * ${table.clearComment} 管理控制器类。
 * Created by wangzhiming on ${genDate}.
 */
@Module(value = ${table.upperCaseName}Controller.MODULE_NAME, order = 3)
@RestController
@RequestMapping(${table.upperCaseName}Controller.URL_PREFIX)
public class ${table.upperCaseName}Controller implements ManageControllerInterface {
    private static final Logger log = LoggerFactory.getLogger(${table.upperCaseName}Controller.class);

    public static final String MODULE_NAME = "${table.clearComment}";
    public static final String URL_PREFIX = "/api${viewPath}";

    @Autowired
    private ${table.upperCaseName}Service ${table.camelCaseName}Service;


    /**
     * GET /list : get ${table.upperCaseName} list
     *
     * @param pagination to paging
     * @param query params
     * @return the ResponseEntity with status 200 (OK) and with body the ${table.upperCaseName} list
     */
    @Command(MODULE_NAME + " list")
    @RequestMapping(value = LIST_URL, method = RequestMethod.GET)
    public ResponseEntity list(Pagination pagination, Query query) {

        List<${table.upperCaseName}> ${table.camelCaseName}List
                = ${table.camelCaseName}Service.getList(pagination, query.getFilter());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(pagination, URL_PREFIX);
        return new ResponseEntity<>(${table.camelCaseName}List, headers, HttpStatus.OK);
    }

    /**
    * PUT /edit : Updates an existing ${table.upperCaseName}.
    *
    * @param ${table.camelCaseName} the user to update
    * @return the ResponseEntity with status 200 (OK) and with body the updated user
    */
    @Command(MODULE_NAME + " edit")
    @RequestMapping(value = EDIT_URL, method = RequestMethod.PUT)
    public ResponseEntity<Integer> update(@Valid @RequestBody ${table.upperCaseName} ${table.camelCaseName})
             throws ServiceProcessException {
        log.debug("REST request to update User : {}", ${table.camelCaseName});
        // ${table.upperCaseName} existing${table.upperCaseName} =
        //        ${table.camelCaseName}Service.getByUsername(${table.camelCaseName}.getUsername());

        // if (existing${table.upperCaseName} != null &&
        //      (!existing${table.upperCaseName}.getUsername().equals(${table.camelCaseName}.getUsername()))) {
        //     throw new LoginAlreadyUsedException();
        // }
        int res = ${table.camelCaseName}Service.update(${table.camelCaseName});

        return ResponseUtil.wrapOrNotFound(res,
            HeaderUtil.createEntityUpdateAlert(${table.camelCaseName}.getId().toString()));
    }

    /**
    * POST /new : Creates a new ${table.upperCaseName}.
    *
    * @param ${table.camelCaseName} the ${table.camelCaseName} to create
    * @return the ResponseEntity with status 200 (OK) and with body the created ${table.camelCaseName}
    * @throws ServiceProcessException 业务处理异常
    * @throws URISyntaxException url syntax Exception
    */
    @Command(MODULE_NAME + " new")
    @RequestMapping(value = NEW_URL, method = RequestMethod.POST)
    public ResponseEntity<${table.upperCaseName}> newUser(@Valid @RequestBody ${table.upperCaseName} ${table.camelCaseName})
        throws ServiceProcessException, URISyntaxException {
        if (${table.camelCaseName}.getId() != null) {
            throw new BadRequestAlertException("A new user cannot already have an ID", "${table.camelCaseName}", "idexists");
            // Lowercase the user login before comparing with database
        // } else if (${table.camelCaseName}Service.getByUsername(${table.camelCaseName}.getUsername()) != null) {
        //    throw new LoginAlreadyUsedException();
        } else {
            Integer res = ${table.camelCaseName}Service.insert(${table.camelCaseName});
            return ResponseEntity.created(new URI(URL_PREFIX + "${table.lowerCaseSubName}/detail?id=" + ${table.camelCaseName}.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(${table.camelCaseName}.getId().toString()))
            .body(${table.camelCaseName});
        }
    }

    /**
    * GET /detail?id={id} : get the "id" ${table.camelCaseName}.
    *
    * @param id the id of the ${table.camelCaseName} to find
    * @return the ResponseEntity with status 200 (OK) and with body the "id" ${table.camelCaseName}, or with status 404 (Not Found)
    */
    @Command(MODULE_NAME + " detail")
    @RequestMapping(value = DETAIL_URL, method = RequestMethod.GET)
    public ResponseEntity<${table.upperCaseName}> get(@RequestParam Long id) {
        return ResponseUtil.wrapOrNotFound(Optional.of(${table.camelCaseName}Service.get(id)));
    }

    /**
    * DELETE /delete?ids={ids} : delete id in the "ids" ${table.upperCaseName}.
    *
    * @param ids id in the ids of the user to delete
    * @return the ResponseEntity with status 200 (OK)
    */
    @Command(MODULE_NAME + " delete")
    @RequestMapping(value = DELETE_URL, method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@RequestParam String ids) throws ServiceProcessException {
        ${table.camelCaseName}Service.batchDelete(LongTools.parseList(ids));
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ids)).build();
    }


}
