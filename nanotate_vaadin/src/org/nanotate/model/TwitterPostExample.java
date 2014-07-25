package org.nanotate.model;

import java.util.ArrayList;
import java.util.List;

public class TwitterPostExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TwitterPostExample() {
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

        public Criteria andTwitter_post_idIsNull() {
            addCriterion("twitter_post_id is null");
            return (Criteria) this;
        }

        public Criteria andTwitter_post_idIsNotNull() {
            addCriterion("twitter_post_id is not null");
            return (Criteria) this;
        }

        public Criteria andTwitter_post_idEqualTo(Integer value) {
            addCriterion("twitter_post_id =", value, "twitter_post_id");
            return (Criteria) this;
        }

        public Criteria andTwitter_post_idNotEqualTo(Integer value) {
            addCriterion("twitter_post_id <>", value, "twitter_post_id");
            return (Criteria) this;
        }

        public Criteria andTwitter_post_idGreaterThan(Integer value) {
            addCriterion("twitter_post_id >", value, "twitter_post_id");
            return (Criteria) this;
        }

        public Criteria andTwitter_post_idGreaterThanOrEqualTo(Integer value) {
            addCriterion("twitter_post_id >=", value, "twitter_post_id");
            return (Criteria) this;
        }

        public Criteria andTwitter_post_idLessThan(Integer value) {
            addCriterion("twitter_post_id <", value, "twitter_post_id");
            return (Criteria) this;
        }

        public Criteria andTwitter_post_idLessThanOrEqualTo(Integer value) {
            addCriterion("twitter_post_id <=", value, "twitter_post_id");
            return (Criteria) this;
        }

        public Criteria andTwitter_post_idIn(List<Integer> values) {
            addCriterion("twitter_post_id in", values, "twitter_post_id");
            return (Criteria) this;
        }

        public Criteria andTwitter_post_idNotIn(List<Integer> values) {
            addCriterion("twitter_post_id not in", values, "twitter_post_id");
            return (Criteria) this;
        }

        public Criteria andTwitter_post_idBetween(Integer value1, Integer value2) {
            addCriterion("twitter_post_id between", value1, value2, "twitter_post_id");
            return (Criteria) this;
        }

        public Criteria andTwitter_post_idNotBetween(Integer value1, Integer value2) {
            addCriterion("twitter_post_id not between", value1, value2, "twitter_post_id");
            return (Criteria) this;
        }

        public Criteria andAnnotation_idIsNull() {
            addCriterion("annotation_id is null");
            return (Criteria) this;
        }

        public Criteria andAnnotation_idIsNotNull() {
            addCriterion("annotation_id is not null");
            return (Criteria) this;
        }

        public Criteria andAnnotation_idEqualTo(String value) {
            addCriterion("annotation_id =", value, "annotation_id");
            return (Criteria) this;
        }

        public Criteria andAnnotation_idNotEqualTo(String value) {
            addCriterion("annotation_id <>", value, "annotation_id");
            return (Criteria) this;
        }

        public Criteria andAnnotation_idGreaterThan(String value) {
            addCriterion("annotation_id >", value, "annotation_id");
            return (Criteria) this;
        }

        public Criteria andAnnotation_idGreaterThanOrEqualTo(String value) {
            addCriterion("annotation_id >=", value, "annotation_id");
            return (Criteria) this;
        }

        public Criteria andAnnotation_idLessThan(String value) {
            addCriterion("annotation_id <", value, "annotation_id");
            return (Criteria) this;
        }

        public Criteria andAnnotation_idLessThanOrEqualTo(String value) {
            addCriterion("annotation_id <=", value, "annotation_id");
            return (Criteria) this;
        }

        public Criteria andAnnotation_idLike(String value) {
            addCriterion("annotation_id like", value, "annotation_id");
            return (Criteria) this;
        }

        public Criteria andAnnotation_idNotLike(String value) {
            addCriterion("annotation_id not like", value, "annotation_id");
            return (Criteria) this;
        }

        public Criteria andAnnotation_idIn(List<String> values) {
            addCriterion("annotation_id in", values, "annotation_id");
            return (Criteria) this;
        }

        public Criteria andAnnotation_idNotIn(List<String> values) {
            addCriterion("annotation_id not in", values, "annotation_id");
            return (Criteria) this;
        }

        public Criteria andAnnotation_idBetween(String value1, String value2) {
            addCriterion("annotation_id between", value1, value2, "annotation_id");
            return (Criteria) this;
        }

        public Criteria andAnnotation_idNotBetween(String value1, String value2) {
            addCriterion("annotation_id not between", value1, value2, "annotation_id");
            return (Criteria) this;
        }

        public Criteria andTwitter_post_urlIsNull() {
            addCriterion("twitter_post_url is null");
            return (Criteria) this;
        }

        public Criteria andTwitter_post_urlIsNotNull() {
            addCriterion("twitter_post_url is not null");
            return (Criteria) this;
        }

        public Criteria andTwitter_post_urlEqualTo(String value) {
            addCriterion("twitter_post_url =", value, "twitter_post_url");
            return (Criteria) this;
        }

        public Criteria andTwitter_post_urlNotEqualTo(String value) {
            addCriterion("twitter_post_url <>", value, "twitter_post_url");
            return (Criteria) this;
        }

        public Criteria andTwitter_post_urlGreaterThan(String value) {
            addCriterion("twitter_post_url >", value, "twitter_post_url");
            return (Criteria) this;
        }

        public Criteria andTwitter_post_urlGreaterThanOrEqualTo(String value) {
            addCriterion("twitter_post_url >=", value, "twitter_post_url");
            return (Criteria) this;
        }

        public Criteria andTwitter_post_urlLessThan(String value) {
            addCriterion("twitter_post_url <", value, "twitter_post_url");
            return (Criteria) this;
        }

        public Criteria andTwitter_post_urlLessThanOrEqualTo(String value) {
            addCriterion("twitter_post_url <=", value, "twitter_post_url");
            return (Criteria) this;
        }

        public Criteria andTwitter_post_urlLike(String value) {
            addCriterion("twitter_post_url like", value, "twitter_post_url");
            return (Criteria) this;
        }

        public Criteria andTwitter_post_urlNotLike(String value) {
            addCriterion("twitter_post_url not like", value, "twitter_post_url");
            return (Criteria) this;
        }

        public Criteria andTwitter_post_urlIn(List<String> values) {
            addCriterion("twitter_post_url in", values, "twitter_post_url");
            return (Criteria) this;
        }

        public Criteria andTwitter_post_urlNotIn(List<String> values) {
            addCriterion("twitter_post_url not in", values, "twitter_post_url");
            return (Criteria) this;
        }

        public Criteria andTwitter_post_urlBetween(String value1, String value2) {
            addCriterion("twitter_post_url between", value1, value2, "twitter_post_url");
            return (Criteria) this;
        }

        public Criteria andTwitter_post_urlNotBetween(String value1, String value2) {
            addCriterion("twitter_post_url not between", value1, value2, "twitter_post_url");
            return (Criteria) this;
        }

        public Criteria andAutomatic_postIsNull() {
            addCriterion("automatic_post is null");
            return (Criteria) this;
        }

        public Criteria andAutomatic_postIsNotNull() {
            addCriterion("automatic_post is not null");
            return (Criteria) this;
        }

        public Criteria andAutomatic_postEqualTo(Boolean value) {
            addCriterion("automatic_post =", value, "automatic_post");
            return (Criteria) this;
        }

        public Criteria andAutomatic_postNotEqualTo(Boolean value) {
            addCriterion("automatic_post <>", value, "automatic_post");
            return (Criteria) this;
        }

        public Criteria andAutomatic_postGreaterThan(Boolean value) {
            addCriterion("automatic_post >", value, "automatic_post");
            return (Criteria) this;
        }

        public Criteria andAutomatic_postGreaterThanOrEqualTo(Boolean value) {
            addCriterion("automatic_post >=", value, "automatic_post");
            return (Criteria) this;
        }

        public Criteria andAutomatic_postLessThan(Boolean value) {
            addCriterion("automatic_post <", value, "automatic_post");
            return (Criteria) this;
        }

        public Criteria andAutomatic_postLessThanOrEqualTo(Boolean value) {
            addCriterion("automatic_post <=", value, "automatic_post");
            return (Criteria) this;
        }

        public Criteria andAutomatic_postIn(List<Boolean> values) {
            addCriterion("automatic_post in", values, "automatic_post");
            return (Criteria) this;
        }

        public Criteria andAutomatic_postNotIn(List<Boolean> values) {
            addCriterion("automatic_post not in", values, "automatic_post");
            return (Criteria) this;
        }

        public Criteria andAutomatic_postBetween(Boolean value1, Boolean value2) {
            addCriterion("automatic_post between", value1, value2, "automatic_post");
            return (Criteria) this;
        }

        public Criteria andAutomatic_postNotBetween(Boolean value1, Boolean value2) {
            addCriterion("automatic_post not between", value1, value2, "automatic_post");
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