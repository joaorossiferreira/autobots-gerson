package com.example.demo.hateoas;

import java.util.ArrayList;
import java.util.List;

public class HateoasResource<T> {
    private T content;
    private List<Link> links = new ArrayList<>();

    public HateoasResource(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public void addLink(String href, String rel) {
        links.add(new Link(href, rel));
    }
}