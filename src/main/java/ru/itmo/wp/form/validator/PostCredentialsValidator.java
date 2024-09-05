package ru.itmo.wp.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.itmo.wp.form.PostCredentials;

@Component
public class PostCredentialsValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(PostCredentials.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (!errors.hasErrors()) {
            PostCredentials postForm = (PostCredentials) target;

            if (postForm.getTags().trim().isEmpty()) {
                return;
            }

            String[] tags = postForm.getTags().split("\\s+");
            for (String tag : tags) {

                if (tag.isEmpty()) {
                    continue;
                }

                if (!tag.matches("[a-zA-Z]+")) {
                    errors.rejectValue("tags", "tags.no-mathe", "Problem with tags");
                    return;
                }
            }

        }
    }
}
