package com.example.postmanpractice;

import java.util.ArrayList;

public class PostManGify {
    public class Codebeautify {
        ArrayList< Object > data = new ArrayList < Object > ();
        Pagination PaginationObject;
        Meta MetaObject;


        // Getter Methods

        public Pagination getPagination() {
            return PaginationObject;
        }

        public Meta getMeta() {
            return MetaObject;
        }

        // Setter Methods

        public void setPagination(Pagination paginationObject) {
            this.PaginationObject = paginationObject;
        }

        public void setMeta(Meta metaObject) {
            this.MetaObject = metaObject;
        }
    }
    public class Meta {
        private float status;
        private String msg;
        private String response_id;


        // Getter Methods

        public float getStatus() {
            return status;
        }

        public String getMsg() {
            return msg;
        }

        public String getResponse_id() {
            return response_id;
        }

        // Setter Methods

        public void setStatus(float status) {
            this.status = status;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public void setResponse_id(String response_id) {
            this.response_id = response_id;
        }
    }
    public class Pagination {
        private float total_count;
        private float count;
        private float offset;


        // Getter Methods

        public float getTotal_count() {
            return total_count;
        }

        public float getCount() {
            return count;
        }

        public float getOffset() {
            return offset;
        }

        // Setter Methods

        public void setTotal_count(float total_count) {
            this.total_count = total_count;
        }

        public void setCount(float count) {
            this.count = count;
        }

        public void setOffset(float offset) {
            this.offset = offset;
        }
    }

}
