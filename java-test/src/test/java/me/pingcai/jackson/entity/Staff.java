package me.pingcai.jackson.entity;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import me.pingcai.util.DateUtils;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author huangpingcai
 * @since 2019/1/4 16:12
 */
@Data
@RequiredArgsConstructor(staticName = "build")
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"salary"}) // 序列化和反序列化时都忽略
public class Staff {

    private long id;

    @NonNull
    @JsonView(ViewMode.Normal.class)
    private String name;

    private String desc;

    @JsonView(ViewMode.Admin.class)
    private long salary;

    @JsonProperty("level")
    private Position position;

    @JsonView(ViewMode.Admin.class)
    private List<Staff> subStaff;

    @JsonFormat(pattern = DateUtils.DEFAULT_DATETIME_PATTERN)
    private Date birthday;

}
