package com.clientui.web.proxies;

import com.clientui.beans.BookBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "library-consumer", url = "localhost:9005")
public interface MicroserviceBookProxy {

        @GetMapping(value = "/books")
        List<BookBean> findBooks();

        @GetMapping("books/user/{borrowerId}")
        List<BookBean> borrowerBooks(@PathVariable Long borrowerId);

}
