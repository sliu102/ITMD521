import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapred.lib.db.DBWritable;
import com.cloudera.sqoop.lib.JdbcWritableBridge;
import com.cloudera.sqoop.lib.DelimiterSet;
import com.cloudera.sqoop.lib.FieldFormatter;
import com.cloudera.sqoop.lib.RecordParser;
import com.cloudera.sqoop.lib.BooleanParser;
import com.cloudera.sqoop.lib.BlobRef;
import com.cloudera.sqoop.lib.ClobRef;
import com.cloudera.sqoop.lib.LargeObjectLoader;
import com.cloudera.sqoop.lib.SqoopRecord;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Logs extends SqoopRecord  implements DBWritable, Writable {
  private final int PROTOCOL_VERSION = 3;
  public int getClassFormatVersion() { return PROTOCOL_VERSION; }
  protected ResultSet __cur_result_set;
  private String dat;
  private String url;
  private Integer status;
  public String get_dat() {
    return dat;
  }
  public void set_dat(String dat) {
    this.dat = dat;
  }
  public Logs with_dat(String dat) {
    this.dat = dat;
    return this;
  }

  public String get_url() {
    return url;
  }
  public void set_url(String url) {
    this.url = url;
  }
  public Logs with_url(String url) {
    this.url = url;
    return this;
  }
  
  public Integer get_status() {
    return status;
  }
  public void set_status(Integer status) {
    this.status = status;
  }
  public Logs with_status(Integer status) {
    this.status = status;
    return this;
  }
  
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Logs)) {
      return false;
    }
    Logs that = (Logs) o;
    boolean equal = true;
    equal = equal && (this.dat == null ? that.dat == null : this.dat.equals(that.dat));
    equal = equal && (this.url == null ? that.url == null : this.url.equals(that.url));
    equal = equal && (this.status == null ? that.status == null : this.status.equals(that.status));
    return equal;
  }
  public boolean equals0(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Logs)) {
      return false;
    }
    Logs that = (Logs) o;
    boolean equal = true;
    equal = equal && (this.dat == null ? that.dat == null : this.dat.equals(that.dat));
    equal = equal && (this.url == null ? that.url == null : this.url.equals(that.url));
    equal = equal && (this.status == null ? that.status == null : this.status.equals(that.status));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.dat = JdbcWritableBridge.readString(1, __dbResults);
    this.url = JdbcWritableBridge.readString(4, __dbResults);
    this.status = JdbcWritableBridge.readInteger(10, __dbResults);
  }
  public void readFields0(ResultSet __dbResults) throws SQLException {
    this.dat = JdbcWritableBridge.readString(1, __dbResults);
    this.url = JdbcWritableBridge.readString(4, __dbResults);
    this.status = JdbcWritableBridge.readInteger(10, __dbResults);
  }
  public void loadLargeObjects(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void loadLargeObjects0(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void write(PreparedStatement __dbStmt) throws SQLException {
    write(__dbStmt, 0);
  }

  public int write(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeString(dat, 1 + __off, 1, __dbStmt);
    JdbcWritableBridge.writeString(url, 2 + __off, 1, __dbStmt);
    JdbcWritableBridge.writeInteger(status, 3 + __off, 1, __dbStmt);
    return 3;
  }
  public void write0(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeString(dat, 1 + __off, 2, __dbStmt);
    JdbcWritableBridge.writeString(url, 2 + __off, 1, __dbStmt);
    JdbcWritableBridge.writeInteger(status, 3 + __off, 1, __dbStmt);
  }
  public void readFields(DataInput __dataIn) throws IOException {
this.readFields0(__dataIn);  }
  public void readFields0(DataInput __dataIn) throws IOException {
    if (__dataIn.readBoolean()) { 
        this.dat = null;
    } else {
    this.dat = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.url = null;
    } else {
    this.url = Text.readString(__dataIn);
    }
    
    if (__dataIn.readBoolean()) { 
        this.status = null;
    } else {
    this.status = Integer.valueOf(__dataIn.readInt());
    }
  }
  public void write(DataOutput __dataOut) throws IOException {
    if (null == this.dat) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, dat);
    }
    if (null == this.url) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, url);
    }
    if (null == this.status) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.status);
    }
  }
  public void write0(DataOutput __dataOut) throws IOException {
    if (null == this.dat) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, dat);
    }
    if (null == this.url) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, url);
    }
    if (null == this.status) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.status);
    }
  }
  private static final DelimiterSet __outputDelimiters = new DelimiterSet((char) 44, (char) 10, (char) 0, (char) 0, false);
  public String toString() {
    return toString(__outputDelimiters, true);
  }
  public String toString(DelimiterSet delimiters) {
    return toString(delimiters, true);
  }
  public String toString(boolean useRecordDelim) {
    return toString(__outputDelimiters, useRecordDelim);
  }
  public String toString(DelimiterSet delimiters, boolean useRecordDelim) {
    StringBuilder __sb = new StringBuilder();
    char fieldDelim = delimiters.getFieldsTerminatedBy();
    __sb.append(FieldFormatter.escapeAndEnclose(dat==null?"null":dat, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(url==null?"null":url, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(status==null?"null":"" + status, delimiters));
    if (useRecordDelim) {
      __sb.append(delimiters.getLinesTerminatedBy());
    }
    return __sb.toString();
  }
  public void toString0(DelimiterSet delimiters, StringBuilder __sb, char fieldDelim) {
       __sb.append(FieldFormatter.escapeAndEnclose(dat==null?"null":dat, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(url==null?"null":url, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(status==null?"null":"" + status, delimiters));
  }
  private static final DelimiterSet __inputDelimiters = new DelimiterSet((char) 44, (char) 10, (char) 0, (char) 0, false);
  private RecordParser __parser;
  public void parse(Text __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(CharSequence __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(byte [] __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(char [] __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(ByteBuffer __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(CharBuffer __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  private void __loadFromFields(List<String> fields) {
    Iterator<String> __it = fields.listIterator();
    String __cur_str = null;
    try {
    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.dat = null; } else {
      this.dat = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.url = null; } else {
      this.url = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.status = null; } else {
      this.status = Integer.valueOf(__cur_str);
    }

  

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  private void __loadFromFields0(Iterator<String> __it) {
    String __cur_str = null;
    try {
    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.dat = null; } else {
      this.dat = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.url = null; } else {
      this.url = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.status = null; } else {
      this.status = Integer.valueOf(__cur_str);
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new TreeMap<String, Object>();
    __sqoop$field_map.put("dat", this.dat);
    __sqoop$field_map.put("url", this.url);
    __sqoop$field_map.put("status", this.status);
    return __sqoop$field_map;
  }

  public void getFieldMap0(Map<String, Object> __sqoop$field_map) {
    __sqoop$field_map.put("dat", this.dat);
    __sqoop$field_map.put("url", this.url);
    __sqoop$field_map.put("status", this.status);
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if ("dat".equals(__fieldName)) {
      this.dat = (String) __fieldVal;
    }
    else    if ("url".equals(__fieldName)) {
      this.url = (String) __fieldVal;
    }
    
    else    if ("status".equals(__fieldName)) {
      this.status = (Integer) __fieldVal;
    }
    
    else {
      throw new RuntimeException("No such field: " + __fieldName);
    }
  }
  public boolean setField0(String __fieldName, Object __fieldVal) {
    if ("dat".equals(__fieldName)) {
      this.dat = (String) __fieldVal;
      return true;
    }
    else    if ("url".equals(__fieldName)) {
      this.url = (String) __fieldVal;
      return true;
    }
    
     else    if ("status".equals(__fieldName)) {
      this.status = (Integer) __fieldVal;
      return true;
    }
    else {
      return false;    }
  }
}