package com.zhenhui.sparkler.data.model.validator;

import com.zhenhui.sparkler.data.model.core.post.Post;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PostValidator implements ConstraintValidator<ValidPost, Post> {

    @Override
    public void initialize(ValidPost constraintAnnotation) {

    }

    @Override
    public boolean isValid(Post post, ConstraintValidatorContext context) {

        switch (post.getType()) {
            case ARTICLE:
            case POST:
                if (post.getContent().getHtml() == null && post.getContent().getMixed() == null) {
                    return false;
                }
                break;
            case SUTRA:
                if (post.getContent().getSutra() == null) {
                    return false;
                }
                break;
            default:
                break;
        }

        return true;
    }

}
