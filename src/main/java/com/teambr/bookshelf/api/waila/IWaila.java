package com.teambr.bookshelf.api.waila;

import java.util.List;

public interface IWaila {
    void returnWailaHead(List<String> tip);

    void returnWailaBody(List<String> tip);

    void returnWailaTail(List<String> tip);
}