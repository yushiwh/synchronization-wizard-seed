package com.jzt.sync.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpParameterParser {
    private static final Logger log = LoggerFactory.getLogger(HttpParameterParser.class);
    private static DateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private Map<String, String[]> parameters;
    private String URI;

    public static void setDateFormat(String format) {
        DEFAULT_DATE_FORMAT = new SimpleDateFormat(format);
    }

    protected HttpParameterParser(HttpServletRequest request) {
        this.parameters = request.getParameterMap();
        this.URI = request.getRequestURI();
    }

    private HttpParameterParser(Map<String, String[]> parameters) {
        this.parameters = parameters;
    }

    public static HttpParameterParser newInstance(HttpServletRequest request) {
        return new HttpParameterParser(request);
    }

    public static HttpParameterParser newInstance(Map<String, String[]> rm) {
        return new HttpParameterParser(rm);
    }

    public Map<String, String[]> getParameters() {
        return this.parameters;
    }

    public String[] getStringArray(String key) {
        List<String> values = new ArrayList();
        String[] params = (String[])this.parameters.get(key);
        if (params != null && params.length != 0) {
            String[] var4 = params;
            int var5 = params.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                String param = var4[var6];
                if (!StringUtils.isEmpty(param)) {
                    values.add(param);
                }
            }

            return (String[])values.toArray(new String[0]);
        } else {
            return (String[])values.toArray(new String[0]);
        }
    }

    public String[] getStringArray(String key, String split) {
        String string = this.getString(key);
        return StringUtils.isEmpty(string) ? null : string.split(split);
    }

    public List<String> getStringList(String key) {
        String[] stringArray = this.getStringArray(key);
        return this.getStringList(stringArray);
    }

    public List<String> getStringList(String key, String split) {
        String[] stringArray = this.getStringArray(key, split);
        return this.getStringList(stringArray);
    }

    private List<String> getStringList(String[] stringArray) {
        if (stringArray != null && stringArray.length != 0) {
            List<String> result = new ArrayList();

            for(int i = 0; i < stringArray.length; ++i) {
                result.add(stringArray[i]);
            }

            return result;
        } else {
            return null;
        }
    }

    public int[] getIntArray(String key) {
        String[] values = this.getStringArray(key);
        if (values != null && values.length != 0) {
            int[] results = new int[values.length];

            for(int i = 0; i < values.length; ++i) {
                String string = values[i];

                try {
                    results[i] = Integer.parseInt(string);
                } catch (Exception var7) {
                    results[i] = 0;
                    log.info("parse int error : " + string, var7);
                }
            }

            return results;
        } else {
            return null;
        }
    }

    public String getString(String key, String defaultValue) {
        String[] values = this.getStringArray(key);
        return values != null && values.length > 0 ? values[0].trim() : defaultValue;
    }

    public boolean containsKey(String key) {
        return this.parameters.containsKey(key);
    }

    public String getString(String key) {
        String[] values = this.getStringArray(key);
        return values != null && values.length > 0 ? values[0].trim() : null;
    }

    public Boolean getBoolean(String key) {
        String str = this.getString(key);
        if (StringUtils.isEmpty(str)) {
            return null;
        } else {
            return !str.equalsIgnoreCase("true") && !str.equals("1") && !str.equals("是") && !str.equalsIgnoreCase("yes") && !str.equalsIgnoreCase("ok") ? Boolean.FALSE : Boolean.TRUE;
        }
    }

    public boolean getBooleanValue(String key) {
        Boolean value = this.getBoolean(key);
        return value == null ? false : value.booleanValue();
    }

    public Integer getInteger(String key) {
        String str = this.getString(key);
        if (StringUtils.isEmpty(str)) {
            return null;
        } else {
            try {
                return Integer.valueOf(str);
            } catch (Exception var4) {
                log.info("parse int error : " + str, var4);
                return null;
            }
        }
    }

    public Integer[] getIntegerArray(String key) {
        String[] values = this.getStringArray(key);
        if (values != null && values.length != 0) {
            Integer[] results = new Integer[values.length];

            for(int i = 0; i < values.length; ++i) {
                String string = values[i];

                try {
                    results[i] = Integer.valueOf(string);
                } catch (Exception var7) {
                    log.info("parse int error : " + string, var7);
                    results[i] = null;
                }
            }

            return results;
        } else {
            return null;
        }
    }

    public int getIntValue(String key, int defaultValue) {
        Integer integer = this.getInteger(key);
        return integer != null ? integer.intValue() : defaultValue;
    }

    public int getIntValue(String key) {
        return this.getIntValue(key, 0);
    }

    public Double getDouble(String key) {
        String str = this.getString(key);
        if (StringUtils.isEmpty(str)) {
            return null;
        } else {
            try {
                return Double.valueOf(str);
            } catch (Exception var4) {
                log.info("parse double error : " + str, var4);
                return null;
            }
        }
    }

    public double getDoubleValue(String key, double defaultValue) {
        Double d = this.getDouble(key);
        return d != null ? d.doubleValue() : defaultValue;
    }

    public double getDoubleValue(String key) {
        return this.getDoubleValue(key, 0.0D);
    }

    public Long getLong(String key) {
        String str = this.getString(key);
        if (StringUtils.isEmpty(str)) {
            return null;
        } else {
            try {
                return Long.valueOf(str);
            } catch (Exception var4) {
                log.info("parse long error : " + str, var4);
                return null;
            }
        }
    }

    public long getLongValue(String key, long defaultValue) {
        Long l = this.getLong(key);
        return l != null ? l.longValue() : defaultValue;
    }

    public long getLongValue(String key) {
        return this.getLongValue(key, 0L);
    }

    public Long[] getLongWrapperArray(String key) {
        String[] values = this.getStringArray(key);
        if (values != null && values.length != 0) {
            Long[] results = new Long[values.length];

            for(int i = 0; i < values.length; ++i) {
                String string = values[i];

                try {
                    results[i] = Long.valueOf(string);
                } catch (Exception var7) {
                    log.info("parse int error : " + string, var7);
                    results[i] = null;
                }
            }

            return results;
        } else {
            return null;
        }
    }

    public long[] getLongArray(String key) {
        String[] values = this.getStringArray(key);
        if (values != null && values.length != 0) {
            long[] results = new long[values.length];

            for(int i = 0; i < values.length; ++i) {
                String string = values[i];

                try {
                    results[i] = Long.parseLong(string);
                } catch (Exception var7) {
                    results[i] = 0L;
                    log.info("parse int error : " + string, var7);
                }
            }

            return results;
        } else {
            return null;
        }
    }

    public Short getShort(String key) {
        String str = this.getString(key);
        if (StringUtils.isEmpty(str)) {
            return null;
        } else {
            try {
                return Short.valueOf(str);
            } catch (Exception var4) {
                log.info("parse short error : " + str, var4);
                return null;
            }
        }
    }

    public Short[] getShortArray(String key) {
        String[] values = this.getStringArray(key);
        if (values != null && values.length != 0) {
            Short[] results = new Short[values.length];

            for(int i = 0; i < values.length; ++i) {
                String string = values[i];

                try {
                    results[i] = Short.valueOf(string);
                } catch (Exception var7) {
                    log.info("parse short error : " + string, var7);
                    results[i] = null;
                }
            }

            return results;
        } else {
            return null;
        }
    }

    public short getShortValue(String key, short defaultValue) {
        Short s = this.getShort(key);
        return s != null ? s.shortValue() : defaultValue;
    }

    public short getShortValue(String key) {
        return this.getShortValue(key, (short)0);
    }

    public Date getDate(String key, String format) {
        Date date = null;
        String str = this.getString(key);
        if (StringUtils.isEmpty(str)) {
            return null;
        } else {
            Object dateFormat = format == null ? DEFAULT_DATE_FORMAT : new SimpleDateFormat(format);

            try {
                date = ((DateFormat)dateFormat).parse(str);
                return date;
            } catch (Exception var7) {
                log.info("parse date error : " + str, var7);
                return null;
            }
        }
    }

    public Date getDate(String key) {
        return this.getDate(key, (String)null);
    }

    public java.sql.Date getSqlDate(String key, String format) {
        Date date = this.getDate(key, format);
        return date != null ? new java.sql.Date(date.getTime()) : null;
    }

    public java.sql.Date getSqlDate(String key) {
        return this.getSqlDate(key, (String)null);
    }

    public BigDecimal getBigDecimal(String key) {
        String value = this.getString(key);
        if (StringUtils.isEmpty(value)) {
            return null;
        } else {
            try {
                return new BigDecimal(value);
            } catch (Exception var4) {
                log.info("parse date error : " + value, var4);
                return null;
            }
        }
    }

    public Calendar getCalendar(String key) {
        Date date = this.getDate(key);
        if (date == null) {
            return null;
        } else {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            return cal;
        }
    }

    public String toParameterString() {
        StringBuilder sb = new StringBuilder(this.URI);
        if (this.parameters != null && this.parameters.size() != 0) {
            sb.append("?");
            Iterator var2 = this.parameters.keySet().iterator();

            while(var2.hasNext()) {
                String key = (String)var2.next();
                String[] values = this.getStringArray(key);
                String[] var5 = values;
                int var6 = values.length;

                for(int var7 = 0; var7 < var6; ++var7) {
                    String value = var5[var7];
                    sb.append(key).append("=").append(value).append("&");
                }
            }

            return sb.toString();
        } else {
            return sb.toString();
        }
    }
}
