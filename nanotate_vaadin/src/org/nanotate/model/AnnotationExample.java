package org.nanotate.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AnnotationExample {

	protected String orderByClause;
	protected boolean distinct;
	protected List<Criteria> oredCriteria;

	public AnnotationExample() {
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

		protected void addCriterion(String condition, Object value,
				String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property
						+ " cannot be null");
			}
			criteria.add(new Criterion(condition, value));
		}

		protected void addCriterion(String condition, Object value1,
				Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property
						+ " cannot be null");
			}
			criteria.add(new Criterion(condition, value1, value2));
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
			addCriterion("annotation_id between", value1, value2,
					"annotation_id");
			return (Criteria) this;
		}

		public Criteria andAnnotation_idNotBetween(String value1, String value2) {
			addCriterion("annotation_id not between", value1, value2,
					"annotation_id");
			return (Criteria) this;
		}

		public Criteria andAnnotator_schema_versionIsNull() {
			addCriterion("annotator_schema_version is null");
			return (Criteria) this;
		}

		public Criteria andAnnotator_schema_versionIsNotNull() {
			addCriterion("annotator_schema_version is not null");
			return (Criteria) this;
		}

		public Criteria andAnnotator_schema_versionEqualTo(String value) {
			addCriterion("annotator_schema_version =", value,
					"annotator_schema_version");
			return (Criteria) this;
		}

		public Criteria andAnnotator_schema_versionNotEqualTo(String value) {
			addCriterion("annotator_schema_version <>", value,
					"annotator_schema_version");
			return (Criteria) this;
		}

		public Criteria andAnnotator_schema_versionGreaterThan(String value) {
			addCriterion("annotator_schema_version >", value,
					"annotator_schema_version");
			return (Criteria) this;
		}

		public Criteria andAnnotator_schema_versionGreaterThanOrEqualTo(
				String value) {
			addCriterion("annotator_schema_version >=", value,
					"annotator_schema_version");
			return (Criteria) this;
		}

		public Criteria andAnnotator_schema_versionLessThan(String value) {
			addCriterion("annotator_schema_version <", value,
					"annotator_schema_version");
			return (Criteria) this;
		}

		public Criteria andAnnotator_schema_versionLessThanOrEqualTo(
				String value) {
			addCriterion("annotator_schema_version <=", value,
					"annotator_schema_version");
			return (Criteria) this;
		}

		public Criteria andAnnotator_schema_versionLike(String value) {
			addCriterion("annotator_schema_version like", value,
					"annotator_schema_version");
			return (Criteria) this;
		}

		public Criteria andAnnotator_schema_versionNotLike(String value) {
			addCriterion("annotator_schema_version not like", value,
					"annotator_schema_version");
			return (Criteria) this;
		}

		public Criteria andAnnotator_schema_versionIn(List<String> values) {
			addCriterion("annotator_schema_version in", values,
					"annotator_schema_version");
			return (Criteria) this;
		}

		public Criteria andAnnotator_schema_versionNotIn(List<String> values) {
			addCriterion("annotator_schema_version not in", values,
					"annotator_schema_version");
			return (Criteria) this;
		}

		public Criteria andAnnotator_schema_versionBetween(String value1,
				String value2) {
			addCriterion("annotator_schema_version between", value1, value2,
					"annotator_schema_version");
			return (Criteria) this;
		}

		public Criteria andAnnotator_schema_versionNotBetween(String value1,
				String value2) {
			addCriterion("annotator_schema_version not between", value1,
					value2, "annotator_schema_version");
			return (Criteria) this;
		}

		public Criteria andCreatedIsNull() {
			addCriterion("created is null");
			return (Criteria) this;
		}

		public Criteria andCreatedIsNotNull() {
			addCriterion("created is not null");
			return (Criteria) this;
		}

		public Criteria andCreatedEqualTo(Date value) {
			addCriterion("created =", value, "created");
			return (Criteria) this;
		}

		public Criteria andCreatedNotEqualTo(Date value) {
			addCriterion("created <>", value, "created");
			return (Criteria) this;
		}

		public Criteria andCreatedGreaterThan(Date value) {
			addCriterion("created >", value, "created");
			return (Criteria) this;
		}

		public Criteria andCreatedGreaterThanOrEqualTo(Date value) {
			addCriterion("created >=", value, "created");
			return (Criteria) this;
		}

		public Criteria andCreatedLessThan(Date value) {
			addCriterion("created <", value, "created");
			return (Criteria) this;
		}

		public Criteria andCreatedLessThanOrEqualTo(Date value) {
			addCriterion("created <=", value, "created");
			return (Criteria) this;
		}

		public Criteria andCreatedIn(List<Date> values) {
			addCriterion("created in", values, "created");
			return (Criteria) this;
		}

		public Criteria andCreatedNotIn(List<Date> values) {
			addCriterion("created not in", values, "created");
			return (Criteria) this;
		}

		public Criteria andCreatedBetween(Date value1, Date value2) {
			addCriterion("created between", value1, value2, "created");
			return (Criteria) this;
		}

		public Criteria andCreatedNotBetween(Date value1, Date value2) {
			addCriterion("created not between", value1, value2, "created");
			return (Criteria) this;
		}

		public Criteria andUpdatedIsNull() {
			addCriterion("updated is null");
			return (Criteria) this;
		}

		public Criteria andUpdatedIsNotNull() {
			addCriterion("updated is not null");
			return (Criteria) this;
		}

		public Criteria andUpdatedEqualTo(Date value) {
			addCriterion("updated =", value, "updated");
			return (Criteria) this;
		}

		public Criteria andUpdatedNotEqualTo(Date value) {
			addCriterion("updated <>", value, "updated");
			return (Criteria) this;
		}

		public Criteria andUpdatedGreaterThan(Date value) {
			addCriterion("updated >", value, "updated");
			return (Criteria) this;
		}

		public Criteria andUpdatedGreaterThanOrEqualTo(Date value) {
			addCriterion("updated >=", value, "updated");
			return (Criteria) this;
		}

		public Criteria andUpdatedLessThan(Date value) {
			addCriterion("updated <", value, "updated");
			return (Criteria) this;
		}

		public Criteria andUpdatedLessThanOrEqualTo(Date value) {
			addCriterion("updated <=", value, "updated");
			return (Criteria) this;
		}

		public Criteria andUpdatedIn(List<Date> values) {
			addCriterion("updated in", values, "updated");
			return (Criteria) this;
		}

		public Criteria andUpdatedNotIn(List<Date> values) {
			addCriterion("updated not in", values, "updated");
			return (Criteria) this;
		}

		public Criteria andUpdatedBetween(Date value1, Date value2) {
			addCriterion("updated between", value1, value2, "updated");
			return (Criteria) this;
		}

		public Criteria andUpdatedNotBetween(Date value1, Date value2) {
			addCriterion("updated not between", value1, value2, "updated");
			return (Criteria) this;
		}

		public Criteria andUriIsNull() {
			addCriterion("uri is null");
			return (Criteria) this;
		}

		public Criteria andUriIsNotNull() {
			addCriterion("uri is not null");
			return (Criteria) this;
		}

		public Criteria andUriEqualTo(String value) {
			addCriterion("uri =", value, "uri");
			return (Criteria) this;
		}

		public Criteria andUriNotEqualTo(String value) {
			addCriterion("uri <>", value, "uri");
			return (Criteria) this;
		}

		public Criteria andUriGreaterThan(String value) {
			addCriterion("uri >", value, "uri");
			return (Criteria) this;
		}

		public Criteria andUriGreaterThanOrEqualTo(String value) {
			addCriterion("uri >=", value, "uri");
			return (Criteria) this;
		}

		public Criteria andUriLessThan(String value) {
			addCriterion("uri <", value, "uri");
			return (Criteria) this;
		}

		public Criteria andUriLessThanOrEqualTo(String value) {
			addCriterion("uri <=", value, "uri");
			return (Criteria) this;
		}

		public Criteria andUriLike(String value) {
			addCriterion("uri like", value, "uri");
			return (Criteria) this;
		}

		public Criteria andUriNotLike(String value) {
			addCriterion("uri not like", value, "uri");
			return (Criteria) this;
		}

		public Criteria andUriIn(List<String> values) {
			addCriterion("uri in", values, "uri");
			return (Criteria) this;
		}

		public Criteria andUriNotIn(List<String> values) {
			addCriterion("uri not in", values, "uri");
			return (Criteria) this;
		}

		public Criteria andUriBetween(String value1, String value2) {
			addCriterion("uri between", value1, value2, "uri");
			return (Criteria) this;
		}

		public Criteria andUriNotBetween(String value1, String value2) {
			addCriterion("uri not between", value1, value2, "uri");
			return (Criteria) this;
		}

		public Criteria andUserIsNull() {
			addCriterion("user is null");
			return (Criteria) this;
		}

		public Criteria andUserIsNotNull() {
			addCriterion("user is not null");
			return (Criteria) this;
		}

		public Criteria andUserEqualTo(String value) {
			addCriterion("user =", value, "user");
			return (Criteria) this;
		}

		public Criteria andUserNotEqualTo(String value) {
			addCriterion("user <>", value, "user");
			return (Criteria) this;
		}

		public Criteria andUserGreaterThan(String value) {
			addCriterion("user >", value, "user");
			return (Criteria) this;
		}

		public Criteria andUserGreaterThanOrEqualTo(String value) {
			addCriterion("user >=", value, "user");
			return (Criteria) this;
		}

		public Criteria andUserLessThan(String value) {
			addCriterion("user <", value, "user");
			return (Criteria) this;
		}

		public Criteria andUserLessThanOrEqualTo(String value) {
			addCriterion("user <=", value, "user");
			return (Criteria) this;
		}

		public Criteria andUserLike(String value) {
			addCriterion("user like", value, "user");
			return (Criteria) this;
		}

		public Criteria andUserNotLike(String value) {
			addCriterion("user not like", value, "user");
			return (Criteria) this;
		}

		public Criteria andUserIn(List<String> values) {
			addCriterion("user in", values, "user");
			return (Criteria) this;
		}

		public Criteria andUserNotIn(List<String> values) {
			addCriterion("user not in", values, "user");
			return (Criteria) this;
		}

		public Criteria andUserBetween(String value1, String value2) {
			addCriterion("user between", value1, value2, "user");
			return (Criteria) this;
		}

		public Criteria andUserNotBetween(String value1, String value2) {
			addCriterion("user not between", value1, value2, "user");
			return (Criteria) this;
		}

		public Criteria andConsumerIsNull() {
			addCriterion("consumer is null");
			return (Criteria) this;
		}

		public Criteria andConsumerIsNotNull() {
			addCriterion("consumer is not null");
			return (Criteria) this;
		}

		public Criteria andConsumerEqualTo(String value) {
			addCriterion("consumer =", value, "consumer");
			return (Criteria) this;
		}

		public Criteria andConsumerNotEqualTo(String value) {
			addCriterion("consumer <>", value, "consumer");
			return (Criteria) this;
		}

		public Criteria andConsumerGreaterThan(String value) {
			addCriterion("consumer >", value, "consumer");
			return (Criteria) this;
		}

		public Criteria andConsumerGreaterThanOrEqualTo(String value) {
			addCriterion("consumer >=", value, "consumer");
			return (Criteria) this;
		}

		public Criteria andConsumerLessThan(String value) {
			addCriterion("consumer <", value, "consumer");
			return (Criteria) this;
		}

		public Criteria andConsumerLessThanOrEqualTo(String value) {
			addCriterion("consumer <=", value, "consumer");
			return (Criteria) this;
		}

		public Criteria andConsumerLike(String value) {
			addCriterion("consumer like", value, "consumer");
			return (Criteria) this;
		}

		public Criteria andConsumerNotLike(String value) {
			addCriterion("consumer not like", value, "consumer");
			return (Criteria) this;
		}

		public Criteria andConsumerIn(List<String> values) {
			addCriterion("consumer in", values, "consumer");
			return (Criteria) this;
		}

		public Criteria andConsumerNotIn(List<String> values) {
			addCriterion("consumer not in", values, "consumer");
			return (Criteria) this;
		}

		public Criteria andConsumerBetween(String value1, String value2) {
			addCriterion("consumer between", value1, value2, "consumer");
			return (Criteria) this;
		}

		public Criteria andConsumerNotBetween(String value1, String value2) {
			addCriterion("consumer not between", value1, value2, "consumer");
			return (Criteria) this;
		}

		public Criteria andStatus_completedIsNull() {
			addCriterion("status_completed is null");
			return (Criteria) this;
		}

		public Criteria andStatus_completedIsNotNull() {
			addCriterion("status_completed is not null");
			return (Criteria) this;
		}

		public Criteria andStatus_completedEqualTo(Boolean value) {
			addCriterion("status_completed =", value, "status_completed");
			return (Criteria) this;
		}

		public Criteria andStatus_completedNotEqualTo(Boolean value) {
			addCriterion("status_completed <>", value, "status_completed");
			return (Criteria) this;
		}

		public Criteria andStatus_completedGreaterThan(Boolean value) {
			addCriterion("status_completed >", value, "status_completed");
			return (Criteria) this;
		}

		public Criteria andStatus_completedGreaterThanOrEqualTo(Boolean value) {
			addCriterion("status_completed >=", value, "status_completed");
			return (Criteria) this;
		}

		public Criteria andStatus_completedLessThan(Boolean value) {
			addCriterion("status_completed <", value, "status_completed");
			return (Criteria) this;
		}

		public Criteria andStatus_completedLessThanOrEqualTo(Boolean value) {
			addCriterion("status_completed <=", value, "status_completed");
			return (Criteria) this;
		}

		public Criteria andStatus_completedIn(List<Boolean> values) {
			addCriterion("status_completed in", values, "status_completed");
			return (Criteria) this;
		}

		public Criteria andStatus_completedNotIn(List<Boolean> values) {
			addCriterion("status_completed not in", values, "status_completed");
			return (Criteria) this;
		}

		public Criteria andStatus_completedBetween(Boolean value1,
				Boolean value2) {
			addCriterion("status_completed between", value1, value2,
					"status_completed");
			return (Criteria) this;
		}

		public Criteria andStatus_completedNotBetween(Boolean value1,
				Boolean value2) {
			addCriterion("status_completed not between", value1, value2,
					"status_completed");
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

		protected Criterion(String condition, Object value, Object secondValue,
				String typeHandler) {
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
	}}