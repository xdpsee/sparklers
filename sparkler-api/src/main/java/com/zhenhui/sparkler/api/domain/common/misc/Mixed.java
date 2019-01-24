package com.zhenhui.sparkler.api.domain.common.misc;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DatabindContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.zhenhui.sparkler.api.domain.common.misc.content.Audio;
import com.zhenhui.sparkler.api.domain.common.misc.content.Image;
import com.zhenhui.sparkler.api.domain.common.misc.content.Text;
import com.zhenhui.sparkler.api.domain.common.misc.content.Video;
import lombok.Data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Data
public class Mixed {

    private List<Content> items = new ArrayList<>();

    @Data
    @JsonTypeInfo(use = JsonTypeInfo.Id.CUSTOM, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
    @JsonTypeIdResolver(ContentResolver.class)
    @JsonSubTypes({
            @JsonSubTypes.Type(name = "text", value = Text.class),
            @JsonSubTypes.Type(name = "image", value = Image.class),
            @JsonSubTypes.Type(name = "audio", value = Audio.class),
            @JsonSubTypes.Type(name = "video", value = Video.class),
    })

    public static abstract class Content {

        public final Integer type;

        protected Content(int type) {
            this.type = type;
        }

    }

    public static class ContentResolver implements TypeIdResolver {

        private JavaType baseType;

        @Override
        public void init(JavaType javaType) {
            this.baseType = javaType;
        }

        @Override
        public String idFromValue(Object o) {
            return idFromValueAndType(o, o.getClass());
        }

        @Override
        public String idFromBaseType() {
            return idFromValueAndType(null, baseType.getRawClass());
        }

        @Override
        public String idFromValueAndType(Object o, Class<?> clazz) {

            try {
                return String.valueOf(clazz.getField("type").get(o));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public JavaType typeFromId(DatabindContext databindContext, String id) throws IOException {

            switch (id) {
                case "1":
                    return TypeFactory.defaultInstance().constructSpecializedType(baseType, Text.class);
                case "2":
                    return TypeFactory.defaultInstance().constructSpecializedType(baseType, Image.class);
                case "3":
                    return TypeFactory.defaultInstance().constructSpecializedType(baseType, Audio.class);
                case "4":
                    return TypeFactory.defaultInstance().constructSpecializedType(baseType, Video.class);

            }

            return null;
        }

        @Override
        public String getDescForKnownTypeIds() {
            return "";
        }

        @Override
        public JsonTypeInfo.Id getMechanism() {
            return JsonTypeInfo.Id.CUSTOM;
        }
    }
}
