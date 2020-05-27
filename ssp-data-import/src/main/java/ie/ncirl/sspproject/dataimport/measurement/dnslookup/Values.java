package ie.ncirl.sspproject.dataimport.measurement.dnslookup;

import ie.ncirl.sspproject.dataimport.measurement.Measurement;

public class Values extends Measurement {
    public String time_ms;
    public String CalledByLocation;
    public String MeasurementLatitude;
    public String real_hostname;
    public String address;
    public String MeasurementLongitude;
    public String error;
    public String context_results;
    public String qclass;
    public String qtype;
    public String results;
    public String target;
    public String rdata_0_0;
    public String rdata_0_1;
    public String rdata_0_2;
    public String rdata_0_3;
    public String rdata_0_4;
    public String rdata_0_5;
    public String name_0_0;
    public String name_0_1;
    public String name_0_2;
    public String name_0_3;
    public String name_0_4;
    public String name_0_5;
    public String respTime_0;
    public String respTime_1;
    public String respTime_2;
    public String respTime_3;
    public String respId_0;
    public String respId_1;
    public String respId_2;
    public String respId_3;
    public String qryId_0;
    public String qryId_1;
    public String qryId_2;
    public String qryId_3;
    public String rcode_0;
    public String rcode_1;
    public String rcode_2;
    public String rcode_3;
    public String qclass_0;
    public String qclass_1;
    public String qclass_2;
    public String qclass_3;
    public String payload_0;
    public String payload_1;
    public String payload_2;
    public String payload_3;
    public String qtype_0;
    public String qtype_1;
    public String qtype_2;
    public String qtype_3;
    public String domain_0;
    public String domain_1;
    public String domain_2;
    public String domain_3;
    public String rtype_0_0;
    public String rtype_0_1;
    public String rtype_0_2;
    public String rtype_0_3;
    public String rtype_0_4;
    public String rtype_0_5;
    public String tc_0;
    public String tc_1;
    public String tc_2;
    public String tc_3;
    public String server_0;
    public String server_1;
    public String server_2;
    public String server_3;
    public String isValid_0;
    public String isValid_1;
    public String isValid_2;
    public String isValid_3;
    public String answers_0;
    public String answers_1;
    public String answers_2;
    public String answers_3;
    public String queries;
    public String name_1_0;
    public String name_2_0;
    public String name_3_0;
    public String rdata_1_0;
    public String rdata_2_0;
    public String rdata_3_0;
    public String rtype_1_0;
    public String rtype_2_0;
    public String rtype_3_0;
    public String rtype_1_1;
    public String rtype_1_2;
    public String rtype_1_3;
    public String rtype_1_4;
    public String rtype_1_5;
    public String rdata_1_1;
    public String rdata_1_2;
    public String rdata_1_3;
    public String rdata_1_4;
    public String rdata_1_5;
    public String name_2_1;
    public String name_2_2;
    public String name_2_3;
    public String name_2_4;
    public String name_2_5;
    public String rdata_2_1;
    public String rdata_2_2;
    public String rdata_2_3;
    public String rdata_2_4;
    public String rdata_2_5;
    public String name_1_1;
    public String name_1_2;
    public String name_1_3;
    public String name_1_4;
    public String name_1_5;
    public String rtype_2_1;
    public String rtype_2_2;
    public String rtype_2_3;
    public String rtype_2_4;
    public String rtype_2_5;
    public String rtype_3_1;
    public String rtype_3_2;
    public String rtype_3_3;
    public String rtype_3_4;
    public String rtype_3_5;
    public String rdata_3_1;
    public String rdata_3_2;
    public String rdata_3_3;
    public String rdata_3_4;
    public String rdata_3_5;
    public String name_3_1;
    public String name_3_2;
    public String name_3_3;
    public String name_3_4;
    public String name_3_5;

    @Override
    public String toHdr(String sep) {
        StringBuffer sb = new StringBuffer();
        separate(sb, "v_time_ms", sep);
        separate(sb, "v_CalledByLocation", sep);
        separate(sb, "v_MeasurementLatitude", sep);
        separate(sb, "v_real_hostname", sep);
        separate(sb, "v_address", sep);
        separate(sb, "v_MeasurementLongitude", sep);
        separate(sb, "v_error", sep);
        separate(sb, "v_context_results", sep);
        separate(sb, "v_qclass", sep);
        separate(sb, "v_qtype", sep);
        separate(sb, "v_results", sep);
        separate(sb, "v_target", sep);
        separate(sb, "v_rdata_0_0", sep);
        separate(sb, "v_rdata_0_1", sep);
        separate(sb, "v_rdata_0_2", sep);
        separate(sb, "v_rdata_0_3", sep);
        separate(sb, "v_rdata_0_4", sep);
        separate(sb, "v_rdata_0_5", sep);
        separate(sb, "v_name_0_0", sep);
        separate(sb, "v_name_0_1", sep);
        separate(sb, "v_name_0_2", sep);
        separate(sb, "v_name_0_3", sep);
        separate(sb, "v_name_0_4", sep);
        separate(sb, "v_name_0_5", sep);
        separate(sb, "v_respTime_0", sep);
        separate(sb, "v_respTime_1", sep);
        separate(sb, "v_respTime_2", sep);
        separate(sb, "v_respTime_3", sep);
        separate(sb, "v_respId_0", sep);
        separate(sb, "v_respId_1", sep);
        separate(sb, "v_respId_2", sep);
        separate(sb, "v_respId_3", sep);
        separate(sb, "v_qryId_0", sep);
        separate(sb, "v_qryId_1", sep);
        separate(sb, "v_qryId_2", sep);
        separate(sb, "v_qryId_3", sep);
        separate(sb, "v_rcode_0", sep);
        separate(sb, "v_rcode_1", sep);
        separate(sb, "v_rcode_2", sep);
        separate(sb, "v_rcode_3", sep);
        separate(sb, "v_qclass_0", sep);
        separate(sb, "v_qclass_1", sep);
        separate(sb, "v_qclass_2", sep);
        separate(sb, "v_qclass_3", sep);
        separate(sb, "v_payload_0", sep);
        separate(sb, "v_payload_1", sep);
        separate(sb, "v_payload_2", sep);
        separate(sb, "v_payload_3", sep);
        separate(sb, "v_qtype_0", sep);
        separate(sb, "v_qtype_1", sep);
        separate(sb, "v_qtype_2", sep);
        separate(sb, "v_qtype_3", sep);
        separate(sb, "v_domain_0", sep);
        separate(sb, "v_domain_1", sep);
        separate(sb, "v_domain_2", sep);
        separate(sb, "v_domain_3", sep);
        separate(sb, "v_rtype_0_0", sep);
        separate(sb, "v_rtype_0_1", sep);
        separate(sb, "v_rtype_0_2", sep);
        separate(sb, "v_rtype_0_3", sep);
        separate(sb, "v_rtype_0_4", sep);
        separate(sb, "v_rtype_0_5", sep);
        separate(sb, "v_tc_0", sep);
        separate(sb, "v_tc_1", sep);
        separate(sb, "v_tc_2", sep);
        separate(sb, "v_tc_3", sep);
        separate(sb, "v_server_0", sep);
        separate(sb, "v_server_1", sep);
        separate(sb, "v_server_2", sep);
        separate(sb, "v_server_3", sep);
        separate(sb, "v_isValid_0", sep);
        separate(sb, "v_isValid_1", sep);
        separate(sb, "v_isValid_2", sep);
        separate(sb, "v_isValid_3", sep);
        separate(sb, "v_answers_0", sep);
        separate(sb, "v_answers_1", sep);
        separate(sb, "v_answers_2", sep);
        separate(sb, "v_answers_3", sep);
        separate(sb, "v_queries", sep);
        separate(sb, "v_name_1_0", sep);
        separate(sb, "v_name_2_0", sep);
        separate(sb, "v_name_3_0", sep);
        separate(sb, "v_rdata_1_0", sep);
        separate(sb, "v_rdata_2_0", sep);
        separate(sb, "v_rdata_3_0", sep);
        separate(sb, "v_rtype_1_0", sep);
        separate(sb, "v_rtype_2_0", sep);
        separate(sb, "v_rtype_3_0", sep);
        separate(sb, "v_rtype_1_1", sep);
        separate(sb, "v_rtype_1_2", sep);
        separate(sb, "v_rtype_1_3", sep);
        separate(sb, "v_rtype_1_4", sep);
        separate(sb, "v_rtype_1_5", sep);
        separate(sb, "v_rdata_1_1", sep);
        separate(sb, "v_rdata_1_2", sep);
        separate(sb, "v_rdata_1_3", sep);
        separate(sb, "v_rdata_1_4", sep);
        separate(sb, "v_rdata_1_5", sep);
        separate(sb, "v_name_2_1", sep);
        separate(sb, "v_name_2_2", sep);
        separate(sb, "v_name_2_3", sep);
        separate(sb, "v_name_2_4", sep);
        separate(sb, "v_name_2_5", sep);
        separate(sb, "v_rdata_2_1", sep);
        separate(sb, "v_rdata_2_2", sep);
        separate(sb, "v_rdata_2_3", sep);
        separate(sb, "v_rdata_2_4", sep);
        separate(sb, "v_rdata_2_5", sep);
        separate(sb, "v_name_1_1", sep);
        separate(sb, "v_name_1_2", sep);
        separate(sb, "v_name_1_3", sep);
        separate(sb, "v_name_1_4", sep);
        separate(sb, "v_name_1_5", sep);
        separate(sb, "v_rtype_2_1", sep);
        separate(sb, "v_rtype_2_2", sep);
        separate(sb, "v_rtype_2_3", sep);
        separate(sb, "v_rtype_2_4", sep);
        separate(sb, "v_rtype_2_5", sep);
        separate(sb, "v_rtype_3_1", sep);
        separate(sb, "v_rtype_3_2", sep);
        separate(sb, "v_rtype_3_3", sep);
        separate(sb, "v_rtype_3_4", sep);
        separate(sb, "v_rtype_3_5", sep);
        separate(sb, "v_rdata_3_1", sep);
        separate(sb, "v_rdata_3_2", sep);
        separate(sb, "v_rdata_3_3", sep);
        separate(sb, "v_rdata_3_4", sep);
        separate(sb, "v_rdata_3_5", sep);
        separate(sb, "v_name_3_1", sep);
        separate(sb, "v_name_3_2", sep);
        separate(sb, "v_name_3_3", sep);
        separate(sb, "v_name_3_4", sep);
        separate(sb, "v_name_3_5", NO_SEP);

        return(sb.toString());
    }

    @Override
    public String toCsv(String nullValue, String quote, String sep) {
        StringBuffer sb = new StringBuffer();
        quoteAndSeparate(sb, time_ms, quote, sep);
        quoteAndSeparate(sb, CalledByLocation, quote, sep);
        quoteAndSeparate(sb, MeasurementLatitude, quote, sep);
        quoteAndSeparate(sb, real_hostname, quote, sep);
        quoteAndSeparate(sb, address, quote, sep);
        quoteAndSeparate(sb, MeasurementLongitude, quote, sep);
        quoteAndSeparate(sb, error, quote, sep);
        quoteAndSeparate(sb, context_results, quote, sep);
        quoteAndSeparate(sb, qclass, quote, sep);
        quoteAndSeparate(sb, qtype, quote, sep);
        quoteAndSeparate(sb, results, quote, sep);
        quoteAndSeparate(sb, target, quote, sep);
        quoteAndSeparate(sb, rdata_0_0, quote, sep);
        quoteAndSeparate(sb, rdata_0_1, quote, sep);
        quoteAndSeparate(sb, rdata_0_2, quote, sep);
        quoteAndSeparate(sb, rdata_0_3, quote, sep);
        quoteAndSeparate(sb, rdata_0_4, quote, sep);
        quoteAndSeparate(sb, rdata_0_5, quote, sep);
        quoteAndSeparate(sb, name_0_0, quote, sep);
        quoteAndSeparate(sb, name_0_1, quote, sep);
        quoteAndSeparate(sb, name_0_2, quote, sep);
        quoteAndSeparate(sb, name_0_3, quote, sep);
        quoteAndSeparate(sb, name_0_4, quote, sep);
        quoteAndSeparate(sb, name_0_5, quote, sep);
        quoteAndSeparate(sb, respTime_0, quote, sep);
        quoteAndSeparate(sb, respTime_1, quote, sep);
        quoteAndSeparate(sb, respTime_2, quote, sep);
        quoteAndSeparate(sb, respTime_3, quote, sep);
        quoteAndSeparate(sb, respId_0, quote, sep);
        quoteAndSeparate(sb, respId_1, quote, sep);
        quoteAndSeparate(sb, respId_2, quote, sep);
        quoteAndSeparate(sb, respId_3, quote, sep);
        quoteAndSeparate(sb, qryId_0, quote, sep);
        quoteAndSeparate(sb, qryId_1, quote, sep);
        quoteAndSeparate(sb, qryId_2, quote, sep);
        quoteAndSeparate(sb, qryId_3, quote, sep);
        quoteAndSeparate(sb, rcode_0, quote, sep);
        quoteAndSeparate(sb, rcode_1, quote, sep);
        quoteAndSeparate(sb, rcode_2, quote, sep);
        quoteAndSeparate(sb, rcode_3, quote, sep);
        quoteAndSeparate(sb, qclass_0, quote, sep);
        quoteAndSeparate(sb, qclass_1, quote, sep);
        quoteAndSeparate(sb, qclass_2, quote, sep);
        quoteAndSeparate(sb, qclass_3, quote, sep);
        quoteAndSeparate(sb, payload_0, quote, sep);
        quoteAndSeparate(sb, payload_1, quote, sep);
        quoteAndSeparate(sb, payload_2, quote, sep);
        quoteAndSeparate(sb, payload_3, quote, sep);
        quoteAndSeparate(sb, qtype_0, quote, sep);
        quoteAndSeparate(sb, qtype_1, quote, sep);
        quoteAndSeparate(sb, qtype_2, quote, sep);
        quoteAndSeparate(sb, qtype_3, quote, sep);
        quoteAndSeparate(sb, domain_0, quote, sep);
        quoteAndSeparate(sb, domain_1, quote, sep);
        quoteAndSeparate(sb, domain_2, quote, sep);
        quoteAndSeparate(sb, domain_3, quote, sep);
        quoteAndSeparate(sb, rtype_0_0, quote, sep);
        quoteAndSeparate(sb, rtype_0_1, quote, sep);
        quoteAndSeparate(sb, rtype_0_2, quote, sep);
        quoteAndSeparate(sb, rtype_0_3, quote, sep);
        quoteAndSeparate(sb, rtype_0_4, quote, sep);
        quoteAndSeparate(sb, rtype_0_5, quote, sep);
        quoteAndSeparate(sb, tc_0, quote, sep);
        quoteAndSeparate(sb, tc_1, quote, sep);
        quoteAndSeparate(sb, tc_2, quote, sep);
        quoteAndSeparate(sb, tc_3, quote, sep);
        quoteAndSeparate(sb, server_0, quote, sep);
        quoteAndSeparate(sb, server_1, quote, sep);
        quoteAndSeparate(sb, server_2, quote, sep);
        quoteAndSeparate(sb, server_3, quote, sep);
        quoteAndSeparate(sb, isValid_0, quote, sep);
        quoteAndSeparate(sb, isValid_1, quote, sep);
        quoteAndSeparate(sb, isValid_2, quote, sep);
        quoteAndSeparate(sb, isValid_3, quote, sep);
        quoteAndSeparate(sb, answers_0, quote, sep);
        quoteAndSeparate(sb, answers_1, quote, sep);
        quoteAndSeparate(sb, answers_2, quote, sep);
        quoteAndSeparate(sb, answers_3, quote, sep);
        quoteAndSeparate(sb, queries, quote, sep);
        quoteAndSeparate(sb, name_1_0, quote, sep);
        quoteAndSeparate(sb, name_2_0, quote, sep);
        quoteAndSeparate(sb, name_3_0, quote, sep);
        quoteAndSeparate(sb, rdata_1_0, quote, sep);
        quoteAndSeparate(sb, rdata_2_0, quote, sep);
        quoteAndSeparate(sb, rdata_3_0, quote, sep);
        quoteAndSeparate(sb, rtype_1_0, quote, sep);
        quoteAndSeparate(sb, rtype_2_0, quote, sep);
        quoteAndSeparate(sb, rtype_3_0, quote, sep);
        quoteAndSeparate(sb, rtype_1_1, quote, sep);
        quoteAndSeparate(sb, rtype_1_2, quote, sep);
        quoteAndSeparate(sb, rtype_1_3, quote, sep);
        quoteAndSeparate(sb, rtype_1_4, quote, sep);
        quoteAndSeparate(sb, rtype_1_5, quote, sep);
        quoteAndSeparate(sb, rdata_1_1, quote, sep);
        quoteAndSeparate(sb, rdata_1_2, quote, sep);
        quoteAndSeparate(sb, rdata_1_3, quote, sep);
        quoteAndSeparate(sb, rdata_1_4, quote, sep);
        quoteAndSeparate(sb, rdata_1_5, quote, sep);
        quoteAndSeparate(sb, name_2_1, quote, sep);
        quoteAndSeparate(sb, name_2_2, quote, sep);
        quoteAndSeparate(sb, name_2_3, quote, sep);
        quoteAndSeparate(sb, name_2_4, quote, sep);
        quoteAndSeparate(sb, name_2_5, quote, sep);
        quoteAndSeparate(sb, rdata_2_1, quote, sep);
        quoteAndSeparate(sb, rdata_2_2, quote, sep);
        quoteAndSeparate(sb, rdata_2_3, quote, sep);
        quoteAndSeparate(sb, rdata_2_4, quote, sep);
        quoteAndSeparate(sb, rdata_2_5, quote, sep);
        quoteAndSeparate(sb, name_1_1, quote, sep);
        quoteAndSeparate(sb, name_1_2, quote, sep);
        quoteAndSeparate(sb, name_1_3, quote, sep);
        quoteAndSeparate(sb, name_1_4, quote, sep);
        quoteAndSeparate(sb, name_1_5, quote, sep);
        quoteAndSeparate(sb, rtype_2_1, quote, sep);
        quoteAndSeparate(sb, rtype_2_2, quote, sep);
        quoteAndSeparate(sb, rtype_2_3, quote, sep);
        quoteAndSeparate(sb, rtype_2_4, quote, sep);
        quoteAndSeparate(sb, rtype_2_5, quote, sep);
        quoteAndSeparate(sb, rtype_3_1, quote, sep);
        quoteAndSeparate(sb, rtype_3_2, quote, sep);
        quoteAndSeparate(sb, rtype_3_3, quote, sep);
        quoteAndSeparate(sb, rtype_3_4, quote, sep);
        quoteAndSeparate(sb, rtype_3_5, quote, sep);
        quoteAndSeparate(sb, rdata_3_1, quote, sep);
        quoteAndSeparate(sb, rdata_3_2, quote, sep);
        quoteAndSeparate(sb, rdata_3_3, quote, sep);
        quoteAndSeparate(sb, rdata_3_4, quote, sep);
        quoteAndSeparate(sb, rdata_3_5, quote, sep);
        quoteAndSeparate(sb, name_3_1, quote, sep);
        quoteAndSeparate(sb, name_3_2, quote, sep);
        quoteAndSeparate(sb, name_3_3, quote, sep);
        quoteAndSeparate(sb, name_3_4, quote, sep);
        quoteAndSeparate(sb, name_3_5, quote, NO_SEP);
        return(sb.toString());
    }

    @Override
    public String toNullCsv(String nullValue, String sep) {
        StringBuffer sb = new StringBuffer();
        separate(sb, nullValue, sep); // time_ms
        separate(sb, nullValue, sep); // CalledByLocation
        separate(sb, nullValue, sep); // MeasurementLatitude
        separate(sb, nullValue, sep); // real_hostname
        separate(sb, nullValue, sep); // address
        separate(sb, nullValue, sep); // MeasurementLongitude
        separate(sb, nullValue, sep); // error
        separate(sb, nullValue, sep); // context_results
        separate(sb, nullValue, sep); // qclass
        separate(sb, nullValue, sep); // qtype
        separate(sb, nullValue, sep); // results
        separate(sb, nullValue, sep); // target
        separate(sb, nullValue, sep); // rdata_0_0
        separate(sb, nullValue, sep); // rdata_0_1
        separate(sb, nullValue, sep); // rdata_0_2
        separate(sb, nullValue, sep); // rdata_0_3
        separate(sb, nullValue, sep); // rdata_0_4
        separate(sb, nullValue, sep); // rdata_0_5
        separate(sb, nullValue, sep); // name_0_0
        separate(sb, nullValue, sep); // name_0_1
        separate(sb, nullValue, sep); // name_0_2
        separate(sb, nullValue, sep); // name_0_3
        separate(sb, nullValue, sep); // name_0_4
        separate(sb, nullValue, sep); // name_0_5
        separate(sb, nullValue, sep); // respTime_0
        separate(sb, nullValue, sep); // respTime_1
        separate(sb, nullValue, sep); // respTime_2
        separate(sb, nullValue, sep); // respTime_3
        separate(sb, nullValue, sep); // respId_0
        separate(sb, nullValue, sep); // respId_1
        separate(sb, nullValue, sep); // respId_2
        separate(sb, nullValue, sep); // respId_3
        separate(sb, nullValue, sep); // qryId_0
        separate(sb, nullValue, sep); // qryId_1
        separate(sb, nullValue, sep); // qryId_2
        separate(sb, nullValue, sep); // qryId_3
        separate(sb, nullValue, sep); // rcode_0
        separate(sb, nullValue, sep); // rcode_1
        separate(sb, nullValue, sep); // rcode_2
        separate(sb, nullValue, sep); // rcode_3
        separate(sb, nullValue, sep); // qclass_0
        separate(sb, nullValue, sep); // qclass_1
        separate(sb, nullValue, sep); // qclass_2
        separate(sb, nullValue, sep); // qclass_3
        separate(sb, nullValue, sep); // payload_0
        separate(sb, nullValue, sep); // payload_1
        separate(sb, nullValue, sep); // payload_2
        separate(sb, nullValue, sep); // payload_3
        separate(sb, nullValue, sep); // qtype_0
        separate(sb, nullValue, sep); // qtype_1
        separate(sb, nullValue, sep); // qtype_2
        separate(sb, nullValue, sep); // qtype_3
        separate(sb, nullValue, sep); // domain_0
        separate(sb, nullValue, sep); // domain_1
        separate(sb, nullValue, sep); // domain_2
        separate(sb, nullValue, sep); // domain_3
        separate(sb, nullValue, sep); // rtype_0_0
        separate(sb, nullValue, sep); // rtype_0_1
        separate(sb, nullValue, sep); // rtype_0_2
        separate(sb, nullValue, sep); // rtype_0_3
        separate(sb, nullValue, sep); // rtype_0_4
        separate(sb, nullValue, sep); // rtype_0_5
        separate(sb, nullValue, sep); // tc_0
        separate(sb, nullValue, sep); // tc_1
        separate(sb, nullValue, sep); // tc_2
        separate(sb, nullValue, sep); // tc_3
        separate(sb, nullValue, sep); // server_0
        separate(sb, nullValue, sep); // server_1
        separate(sb, nullValue, sep); // server_2
        separate(sb, nullValue, sep); // server_3
        separate(sb, nullValue, sep); // isValid_0
        separate(sb, nullValue, sep); // isValid_1
        separate(sb, nullValue, sep); // isValid_2
        separate(sb, nullValue, sep); // isValid_3
        separate(sb, nullValue, sep); // answers_0
        separate(sb, nullValue, sep); // answers_1
        separate(sb, nullValue, sep); // answers_2
        separate(sb, nullValue, sep); // answers_3
        separate(sb, nullValue, sep); // queries
        separate(sb, nullValue, sep); // name_1_0
        separate(sb, nullValue, sep); // name_2_0
        separate(sb, nullValue, sep); // name_3_0
        separate(sb, nullValue, sep); // rdata_1_0
        separate(sb, nullValue, sep); // rdata_2_0
        separate(sb, nullValue, sep); // rdata_3_0
        separate(sb, nullValue, sep); // rtype_1_0
        separate(sb, nullValue, sep); // rtype_2_0
        separate(sb, nullValue, sep); // rtype_3_0
        separate(sb, nullValue, sep); // rtype_1_1
        separate(sb, nullValue, sep); // rtype_1_2
        separate(sb, nullValue, sep); // rtype_1_3
        separate(sb, nullValue, sep); // rtype_1_4
        separate(sb, nullValue, sep); // rtype_1_5
        separate(sb, nullValue, sep); // rdata_1_1
        separate(sb, nullValue, sep); // rdata_1_2
        separate(sb, nullValue, sep); // rdata_1_3
        separate(sb, nullValue, sep); // rdata_1_4
        separate(sb, nullValue, sep); // rdata_1_5
        separate(sb, nullValue, sep); // name_2_1
        separate(sb, nullValue, sep); // name_2_2
        separate(sb, nullValue, sep); // name_2_3
        separate(sb, nullValue, sep); // name_2_4
        separate(sb, nullValue, sep); // name_2_5
        separate(sb, nullValue, sep); // rdata_2_1
        separate(sb, nullValue, sep); // rdata_2_2
        separate(sb, nullValue, sep); // rdata_2_3
        separate(sb, nullValue, sep); // rdata_2_4
        separate(sb, nullValue, sep); // rdata_2_5
        separate(sb, nullValue, sep); // name_1_1
        separate(sb, nullValue, sep); // name_1_2
        separate(sb, nullValue, sep); // name_1_3
        separate(sb, nullValue, sep); // name_1_4
        separate(sb, nullValue, sep); // name_1_5
        separate(sb, nullValue, sep); // rtype_2_1
        separate(sb, nullValue, sep); // rtype_2_2
        separate(sb, nullValue, sep); // rtype_2_3
        separate(sb, nullValue, sep); // rtype_2_4
        separate(sb, nullValue, sep); // rtype_2_5
        separate(sb, nullValue, sep); // rtype_3_1
        separate(sb, nullValue, sep); // rtype_3_2
        separate(sb, nullValue, sep); // rtype_3_3
        separate(sb, nullValue, sep); // rtype_3_4
        separate(sb, nullValue, sep); // rtype_3_5
        separate(sb, nullValue, sep); // rdata_3_1
        separate(sb, nullValue, sep); // rdata_3_2
        separate(sb, nullValue, sep); // rdata_3_3
        separate(sb, nullValue, sep); // rdata_3_4
        separate(sb, nullValue, sep); // rdata_3_5
        separate(sb, nullValue, sep); // name_3_1
        separate(sb, nullValue, sep); // name_3_2
        separate(sb, nullValue, sep); // name_3_3
        separate(sb, nullValue, sep); // name_3_4
        separate(sb, nullValue, sep); // name_3_5
        return(sb.toString());
    }
}
