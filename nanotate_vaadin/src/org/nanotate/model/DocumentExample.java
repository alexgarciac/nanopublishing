package org.nanotate.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DocumentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DocumentExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andDoc_uuidIsNull() {
            addCriterion("doc_uuid is null");
            return (Criteria) this;
        }

        public Criteria andDoc_uuidIsNotNull() {
            addCriterion("doc_uuid is not null");
            return (Criteria) this;
        }

        public Criteria andDoc_uuidEqualTo(String value) {
            addCriterion("doc_uuid =", value, "doc_uuid");
            return (Criteria) this;
        }

        public Criteria andDoc_uuidNotEqualTo(String value) {
            addCriterion("doc_uuid <>", value, "doc_uuid");
            return (Criteria) this;
        }

        public Criteria andDoc_uuidGreaterThan(String value) {
            addCriterion("doc_uuid >", value, "doc_uuid");
            return (Criteria) this;
        }

        public Criteria andDoc_uuidGreaterThanOrEqualTo(String value) {
            addCriterion("doc_uuid >=", value, "doc_uuid");
            return (Criteria) this;
        }

        public Criteria andDoc_uuidLessThan(String value) {
            addCriterion("doc_uuid <", value, "doc_uuid");
            return (Criteria) this;
        }

        public Criteria andDoc_uuidLessThanOrEqualTo(String value) {
            addCriterion("doc_uuid <=", value, "doc_uuid");
            return (Criteria) this;
        }

        public Criteria andDoc_uuidLike(String value) {
            addCriterion("doc_uuid like", value, "doc_uuid");
            return (Criteria) this;
        }

        public Criteria andDoc_uuidNotLike(String value) {
            addCriterion("doc_uuid not like", value, "doc_uuid");
            return (Criteria) this;
        }

        public Criteria andDoc_uuidIn(List<String> values) {
            addCriterion("doc_uuid in", values, "doc_uuid");
            return (Criteria) this;
        }

        public Criteria andDoc_uuidNotIn(List<String> values) {
            addCriterion("doc_uuid not in", values, "doc_uuid");
            return (Criteria) this;
        }

        public Criteria andDoc_uuidBetween(String value1, String value2) {
            addCriterion("doc_uuid between", value1, value2, "doc_uuid");
            return (Criteria) this;
        }

        public Criteria andDoc_uuidNotBetween(String value1, String value2) {
            addCriterion("doc_uuid not between", value1, value2, "doc_uuid");
            return (Criteria) this;
        }

        public Criteria andDoiIsNull() {
            addCriterion("doi is null");
            return (Criteria) this;
        }

        public Criteria andDoiIsNotNull() {
            addCriterion("doi is not null");
            return (Criteria) this;
        }

        public Criteria andDoiEqualTo(String value) {
            addCriterion("doi =", value, "doi");
            return (Criteria) this;
        }

        public Criteria andDoiNotEqualTo(String value) {
            addCriterion("doi <>", value, "doi");
            return (Criteria) this;
        }

        public Criteria andDoiGreaterThan(String value) {
            addCriterion("doi >", value, "doi");
            return (Criteria) this;
        }

        public Criteria andDoiGreaterThanOrEqualTo(String value) {
            addCriterion("doi >=", value, "doi");
            return (Criteria) this;
        }

        public Criteria andDoiLessThan(String value) {
            addCriterion("doi <", value, "doi");
            return (Criteria) this;
        }

        public Criteria andDoiLessThanOrEqualTo(String value) {
            addCriterion("doi <=", value, "doi");
            return (Criteria) this;
        }

        public Criteria andDoiLike(String value) {
            addCriterion("doi like", value, "doi");
            return (Criteria) this;
        }

        public Criteria andDoiNotLike(String value) {
            addCriterion("doi not like", value, "doi");
            return (Criteria) this;
        }

        public Criteria andDoiIn(List<String> values) {
            addCriterion("doi in", values, "doi");
            return (Criteria) this;
        }

        public Criteria andDoiNotIn(List<String> values) {
            addCriterion("doi not in", values, "doi");
            return (Criteria) this;
        }

        public Criteria andDoiBetween(String value1, String value2) {
            addCriterion("doi between", value1, value2, "doi");
            return (Criteria) this;
        }

        public Criteria andDoiNotBetween(String value1, String value2) {
            addCriterion("doi not between", value1, value2, "doi");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andFull_citationIsNull() {
            addCriterion("full_citation is null");
            return (Criteria) this;
        }

        public Criteria andFull_citationIsNotNull() {
            addCriterion("full_citation is not null");
            return (Criteria) this;
        }

        public Criteria andFull_citationEqualTo(String value) {
            addCriterion("full_citation =", value, "full_citation");
            return (Criteria) this;
        }

        public Criteria andFull_citationNotEqualTo(String value) {
            addCriterion("full_citation <>", value, "full_citation");
            return (Criteria) this;
        }

        public Criteria andFull_citationGreaterThan(String value) {
            addCriterion("full_citation >", value, "full_citation");
            return (Criteria) this;
        }

        public Criteria andFull_citationGreaterThanOrEqualTo(String value) {
            addCriterion("full_citation >=", value, "full_citation");
            return (Criteria) this;
        }

        public Criteria andFull_citationLessThan(String value) {
            addCriterion("full_citation <", value, "full_citation");
            return (Criteria) this;
        }

        public Criteria andFull_citationLessThanOrEqualTo(String value) {
            addCriterion("full_citation <=", value, "full_citation");
            return (Criteria) this;
        }

        public Criteria andFull_citationLike(String value) {
            addCriterion("full_citation like", value, "full_citation");
            return (Criteria) this;
        }

        public Criteria andFull_citationNotLike(String value) {
            addCriterion("full_citation not like", value, "full_citation");
            return (Criteria) this;
        }

        public Criteria andFull_citationIn(List<String> values) {
            addCriterion("full_citation in", values, "full_citation");
            return (Criteria) this;
        }

        public Criteria andFull_citationNotIn(List<String> values) {
            addCriterion("full_citation not in", values, "full_citation");
            return (Criteria) this;
        }

        public Criteria andFull_citationBetween(String value1, String value2) {
            addCriterion("full_citation between", value1, value2, "full_citation");
            return (Criteria) this;
        }

        public Criteria andFull_citationNotBetween(String value1, String value2) {
            addCriterion("full_citation not between", value1, value2, "full_citation");
            return (Criteria) this;
        }

        public Criteria andFirst_authorIsNull() {
            addCriterion("first_author is null");
            return (Criteria) this;
        }

        public Criteria andFirst_authorIsNotNull() {
            addCriterion("first_author is not null");
            return (Criteria) this;
        }

        public Criteria andFirst_authorEqualTo(String value) {
            addCriterion("first_author =", value, "first_author");
            return (Criteria) this;
        }

        public Criteria andFirst_authorNotEqualTo(String value) {
            addCriterion("first_author <>", value, "first_author");
            return (Criteria) this;
        }

        public Criteria andFirst_authorGreaterThan(String value) {
            addCriterion("first_author >", value, "first_author");
            return (Criteria) this;
        }

        public Criteria andFirst_authorGreaterThanOrEqualTo(String value) {
            addCriterion("first_author >=", value, "first_author");
            return (Criteria) this;
        }

        public Criteria andFirst_authorLessThan(String value) {
            addCriterion("first_author <", value, "first_author");
            return (Criteria) this;
        }

        public Criteria andFirst_authorLessThanOrEqualTo(String value) {
            addCriterion("first_author <=", value, "first_author");
            return (Criteria) this;
        }

        public Criteria andFirst_authorLike(String value) {
            addCriterion("first_author like", value, "first_author");
            return (Criteria) this;
        }

        public Criteria andFirst_authorNotLike(String value) {
            addCriterion("first_author not like", value, "first_author");
            return (Criteria) this;
        }

        public Criteria andFirst_authorIn(List<String> values) {
            addCriterion("first_author in", values, "first_author");
            return (Criteria) this;
        }

        public Criteria andFirst_authorNotIn(List<String> values) {
            addCriterion("first_author not in", values, "first_author");
            return (Criteria) this;
        }

        public Criteria andFirst_authorBetween(String value1, String value2) {
            addCriterion("first_author between", value1, value2, "first_author");
            return (Criteria) this;
        }

        public Criteria andFirst_authorNotBetween(String value1, String value2) {
            addCriterion("first_author not between", value1, value2, "first_author");
            return (Criteria) this;
        }

        public Criteria andYearIsNull() {
            addCriterion("year is null");
            return (Criteria) this;
        }

        public Criteria andYearIsNotNull() {
            addCriterion("year is not null");
            return (Criteria) this;
        }

        public Criteria andYearEqualTo(Integer value) {
            addCriterion("year =", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotEqualTo(Integer value) {
            addCriterion("year <>", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearGreaterThan(Integer value) {
            addCriterion("year >", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearGreaterThanOrEqualTo(Integer value) {
            addCriterion("year >=", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearLessThan(Integer value) {
            addCriterion("year <", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearLessThanOrEqualTo(Integer value) {
            addCriterion("year <=", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearIn(List<Integer> values) {
            addCriterion("year in", values, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotIn(List<Integer> values) {
            addCriterion("year not in", values, "year");
            return (Criteria) this;
        }

        public Criteria andYearBetween(Integer value1, Integer value2) {
            addCriterion("year between", value1, value2, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotBetween(Integer value1, Integer value2) {
            addCriterion("year not between", value1, value2, "year");
            return (Criteria) this;
        }

        public Criteria andUploaded_byIsNull() {
            addCriterion("uploaded_by is null");
            return (Criteria) this;
        }

        public Criteria andUploaded_byIsNotNull() {
            addCriterion("uploaded_by is not null");
            return (Criteria) this;
        }

        public Criteria andUploaded_byEqualTo(String value) {
            addCriterion("uploaded_by =", value, "uploaded_by");
            return (Criteria) this;
        }

        public Criteria andUploaded_byNotEqualTo(String value) {
            addCriterion("uploaded_by <>", value, "uploaded_by");
            return (Criteria) this;
        }

        public Criteria andUploaded_byGreaterThan(String value) {
            addCriterion("uploaded_by >", value, "uploaded_by");
            return (Criteria) this;
        }

        public Criteria andUploaded_byGreaterThanOrEqualTo(String value) {
            addCriterion("uploaded_by >=", value, "uploaded_by");
            return (Criteria) this;
        }

        public Criteria andUploaded_byLessThan(String value) {
            addCriterion("uploaded_by <", value, "uploaded_by");
            return (Criteria) this;
        }

        public Criteria andUploaded_byLessThanOrEqualTo(String value) {
            addCriterion("uploaded_by <=", value, "uploaded_by");
            return (Criteria) this;
        }

        public Criteria andUploaded_byLike(String value) {
            addCriterion("uploaded_by like", value, "uploaded_by");
            return (Criteria) this;
        }

        public Criteria andUploaded_byNotLike(String value) {
            addCriterion("uploaded_by not like", value, "uploaded_by");
            return (Criteria) this;
        }

        public Criteria andUploaded_byIn(List<String> values) {
            addCriterion("uploaded_by in", values, "uploaded_by");
            return (Criteria) this;
        }

        public Criteria andUploaded_byNotIn(List<String> values) {
            addCriterion("uploaded_by not in", values, "uploaded_by");
            return (Criteria) this;
        }

        public Criteria andUploaded_byBetween(String value1, String value2) {
            addCriterion("uploaded_by between", value1, value2, "uploaded_by");
            return (Criteria) this;
        }

        public Criteria andUploaded_byNotBetween(String value1, String value2) {
            addCriterion("uploaded_by not between", value1, value2, "uploaded_by");
            return (Criteria) this;
        }

        public Criteria andUpload_dateIsNull() {
            addCriterion("upload_date is null");
            return (Criteria) this;
        }

        public Criteria andUpload_dateIsNotNull() {
            addCriterion("upload_date is not null");
            return (Criteria) this;
        }

        public Criteria andUpload_dateEqualTo(Date value) {
            addCriterion("upload_date =", value, "upload_date");
            return (Criteria) this;
        }

        public Criteria andUpload_dateNotEqualTo(Date value) {
            addCriterion("upload_date <>", value, "upload_date");
            return (Criteria) this;
        }

        public Criteria andUpload_dateGreaterThan(Date value) {
            addCriterion("upload_date >", value, "upload_date");
            return (Criteria) this;
        }

        public Criteria andUpload_dateGreaterThanOrEqualTo(Date value) {
            addCriterion("upload_date >=", value, "upload_date");
            return (Criteria) this;
        }

        public Criteria andUpload_dateLessThan(Date value) {
            addCriterion("upload_date <", value, "upload_date");
            return (Criteria) this;
        }

        public Criteria andUpload_dateLessThanOrEqualTo(Date value) {
            addCriterion("upload_date <=", value, "upload_date");
            return (Criteria) this;
        }

        public Criteria andUpload_dateIn(List<Date> values) {
            addCriterion("upload_date in", values, "upload_date");
            return (Criteria) this;
        }

        public Criteria andUpload_dateNotIn(List<Date> values) {
            addCriterion("upload_date not in", values, "upload_date");
            return (Criteria) this;
        }

        public Criteria andUpload_dateBetween(Date value1, Date value2) {
            addCriterion("upload_date between", value1, value2, "upload_date");
            return (Criteria) this;
        }

        public Criteria andUpload_dateNotBetween(Date value1, Date value2) {
            addCriterion("upload_date not between", value1, value2, "upload_date");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}