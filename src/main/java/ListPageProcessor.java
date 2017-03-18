import com.jayway.jsonpath.JsonPath;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.HashMap;
import java.util.List;

/**
 * Created by wangdong on 2017/2/12.
 */
public class ListPageProcessor implements PageProcessor {

    public static String a="<select name=\"cnt\"> \n" +
            "\n" +
            "<option value=\"AFG_Afghanistan_3\">Afghanistan</option>\n" +
            "<option value=\"XAD_Akrotiri and Dhekelia_2\">Akrotiri and Dhekelia</option>\n" +
            "<option value=\"ALA_Åland_2\">Åland</option>\n" +
            "<option value=\"ALB_Albania_4\">Albania</option>\n" +
            "<option value=\"DZA_Algeria_3\">Algeria</option>\n" +
            "<option value=\"ASM_American Samoa_4\">American Samoa</option>\n" +
            "<option value=\"AND_Andorra_2\">Andorra</option>\n" +
            "<option value=\"AGO_Angola_4\">Angola</option>\n" +
            "<option value=\"AIA_Anguilla_1\">Anguilla</option>\n" +
            "<option value=\"ATA_Antarctica_1\">Antarctica</option>\n" +
            "<option value=\"ATG_Antigua and Barbuda_2\">Antigua and Barbuda</option>\n" +
            "<option value=\"ARG_Argentina_3\">Argentina</option>\n" +
            "<option value=\"ARM_Armenia_2\">Armenia</option>\n" +
            "<option value=\"ABW_Aruba_1\">Aruba</option>\n" +
            "<option value=\"AUS_Australia_3\">Australia</option>\n" +
            "<option value=\"AUT_Austria_4\">Austria</option>\n" +
            "<option value=\"AZE_Azerbaijan_3\">Azerbaijan</option>\n" +
            "<option value=\"BHS_Bahamas_2\">Bahamas</option>\n" +
            "<option value=\"BHR_Bahrain_2\">Bahrain</option>\n" +
            "<option value=\"BGD_Bangladesh_4\">Bangladesh</option>\n" +
            "<option value=\"BRB_Barbados_2\">Barbados</option>\n" +
            "<option value=\"BLR_Belarus_3\">Belarus</option>\n" +
            "<option value=\"BEL_Belgium_5\">Belgium</option>\n" +
            "<option value=\"BLZ_Belize_2\">Belize</option>\n" +
            "<option value=\"BEN_Benin_3\">Benin</option>\n" +
            "<option value=\"BMU_Bermuda_2\">Bermuda</option>\n" +
            "<option value=\"BTN_Bhutan_3\">Bhutan</option>\n" +
            "<option value=\"BOL_Bolivia_4\">Bolivia</option>\n" +
            "<option value=\"BES_Bonaire, Saint Eustatius and Saba_2\">Bonaire, Saint Eustatius and Saba</option>\n" +
            "<option value=\"BIH_Bosnia and Herzegovina_4\">Bosnia and Herzegovina</option>\n" +
            "<option value=\"BWA_Botswana_3\">Botswana</option>\n" +
            "<option value=\"BVT_Bouvet Island_1\">Bouvet Island</option>\n" +
            "<option value=\"BRA_Brazil_4\">Brazil</option>\n" +
            "<option value=\"IOT_British Indian Ocean Territory_1\">British Indian Ocean Territory</option>\n" +
            "<option value=\"VGB_British Virgin Islands_2\">British Virgin Islands</option>\n" +
            "<option value=\"BRN_Brunei_3\">Brunei</option>\n" +
            "<option value=\"BGR_Bulgaria_3\">Bulgaria</option>\n" +
            "<option value=\"BFA_Burkina Faso_4\">Burkina Faso</option>\n" +
            "<option value=\"BDI_Burundi_5\">Burundi</option>\n" +
            "<option value=\"KHM_Cambodia_5\">Cambodia</option>\n" +
            "<option value=\"CMR_Cameroon_4\">Cameroon</option>\n" +
            "<option value=\"CAN_Canada_4\">Canada</option>\n" +
            "<option value=\"CPV_Cape Verde_2\">Cape Verde</option>\n" +
            "<option value=\"XCA_Caspian Sea_1\">Caspian Sea</option>\n" +
            "<option value=\"CYM_Cayman Islands_2\">Cayman Islands</option>\n" +
            "<option value=\"CAF_Central African Republic_3\">Central African Republic</option>\n" +
            "<option value=\"TCD_Chad_4\">Chad</option>\n" +
            "<option value=\"CHL_Chile_4\">Chile</option>\n" +
            "<option value=\"CHN_China_4\">China</option>\n" +
            "<option value=\"CXR_Christmas Island_1\">Christmas Island</option>\n" +
            "<option value=\"XCL_Clipperton Island_1\">Clipperton Island</option>\n" +
            "<option value=\"CCK_Cocos Islands_1\">Cocos Islands</option>\n" +
            "<option value=\"COL_Colombia_3\">Colombia</option>\n" +
            "<option value=\"COM_Comoros_2\">Comoros</option>\n" +
            "<option value=\"COK_Cook Islands_1\">Cook Islands</option>\n" +
            "<option value=\"CRI_Costa Rica_3\">Costa Rica</option>\n" +
            "<option value=\"CIV_Côte d'Ivoire_5\">Côte d'Ivoire</option>\n" +
            "<option value=\"HRV_Croatia_3\">Croatia</option>\n" +
            "<option value=\"CUB_Cuba_3\">Cuba</option>\n" +
            "<option value=\"CUW_Curaçao_1\">Curaçao</option>\n" +
            "<option value=\"CYP_Cyprus_2\">Cyprus</option>\n" +
            "<option value=\"CZE_Czech Republic_3\">Czech Republic</option>\n" +
            "<option value=\"COD_Democratic Republic of the Congo_4\">Democratic Republic of the Congo</option>\n" +
            "<option value=\"DNK_Denmark_3\">Denmark</option>\n" +
            "<option value=\"DJI_Djibouti_3\">Djibouti</option>\n" +
            "<option value=\"DMA_Dominica_2\">Dominica</option>\n" +
            "<option value=\"DOM_Dominican Republic_3\">Dominican Republic</option>\n" +
            "<option value=\"ECU_Ecuador_4\">Ecuador</option>\n" +
            "<option value=\"EGY_Egypt_3\">Egypt</option>\n" +
            "<option value=\"SLV_El Salvador_3\">El Salvador</option>\n" +
            "<option value=\"GNQ_Equatorial Guinea_3\">Equatorial Guinea</option>\n" +
            "<option value=\"ERI_Eritrea_3\">Eritrea</option>\n" +
            "<option value=\"EST_Estonia_4\">Estonia</option>\n" +
            "<option value=\"ETH_Ethiopia_4\">Ethiopia</option>\n" +
            "<option value=\"FLK_Falkland Islands_1\">Falkland Islands</option>\n" +
            "<option value=\"FRO_Faroe Islands_3\">Faroe Islands</option>\n" +
            "<option value=\"FJI_Fiji_3\">Fiji</option>\n" +
            "<option value=\"FIN_Finland_5\">Finland</option>\n" +
            "<option value=\"FRA_France_6\">France</option>\n" +
            "<option value=\"GUF_French Guiana_3\">French Guiana</option>\n" +
            "<option value=\"PYF_French Polynesia_2\">French Polynesia</option>\n" +
            "<option value=\"ATF_French Southern Territories_2\">French Southern Territories</option>\n" +
            "<option value=\"GAB_Gabon_3\">Gabon</option>\n" +
            "<option value=\"GMB_Gambia_3\">Gambia</option>\n" +
            "<option value=\"GEO_Georgia_3\">Georgia</option>\n" +
            "<option value=\"DEU_Germany_5\">Germany</option>\n" +
            "<option value=\"GHA_Ghana_3\">Ghana</option>\n" +
            "<option value=\"GIB_Gibraltar_1\">Gibraltar</option>\n" +
            "<option value=\"GRC_Greece_4\">Greece</option>\n" +
            "<option value=\"GRL_Greenland_2\">Greenland</option>\n" +
            "<option value=\"GRD_Grenada_2\">Grenada</option>\n" +
            "<option value=\"GLP_Guadeloupe_3\">Guadeloupe</option>\n" +
            "<option value=\"GUM_Guam_2\">Guam</option>\n" +
            "<option value=\"GTM_Guatemala_3\">Guatemala</option>\n" +
            "<option value=\"GGY_Guernsey_2\">Guernsey</option>\n" +
            "<option value=\"GIN_Guinea_4\">Guinea</option>\n" +
            "<option value=\"GNB_Guinea-Bissau_3\">Guinea-Bissau</option>\n" +
            "<option value=\"GUY_Guyana_3\">Guyana</option>\n" +
            "<option value=\"HTI_Haiti_5\">Haiti</option>\n" +
            "<option value=\"HMD_Heard Island and McDonald Islands_1\">Heard Island and McDonald Islands</option>\n" +
            "<option value=\"HND_Honduras_3\">Honduras</option>\n" +
            "<option value=\"HKG_Hong Kong_2\">Hong Kong</option>\n" +
            "<option value=\"HUN_Hungary_3\">Hungary</option>\n" +
            "<option value=\"ISL_Iceland_3\">Iceland</option>\n" +
            "<option value=\"IND_India_4\">India</option>\n" +
            "<option value=\"IDN_Indonesia_3\">Indonesia</option>\n" +
            "<option value=\"IRN_Iran_3\">Iran</option>\n" +
            "<option value=\"IRQ_Iraq_3\">Iraq</option>\n" +
            "<option value=\"IRL_Ireland_2\">Ireland</option>\n" +
            "<option value=\"IMN_Isle of Man_3\">Isle of Man</option>\n" +
            "<option value=\"ISR_Israel_2\">Israel</option>\n" +
            "<option value=\"ITA_Italy_4\">Italy</option>\n" +
            "<option value=\"JAM_Jamaica_2\">Jamaica</option>\n" +
            "<option value=\"JPN_Japan_3\">Japan</option>\n" +
            "<option value=\"JEY_Jersey_2\">Jersey</option>\n" +
            "<option value=\"JOR_Jordan_3\">Jordan</option>\n" +
            "<option value=\"KAZ_Kazakhstan_3\">Kazakhstan</option>\n" +
            "<option value=\"KEN_Kenya_4\">Kenya</option>\n" +
            "<option value=\"KIR_Kiribati_1\">Kiribati</option>\n" +
            "<option value=\"XKO_Kosovo_3\">Kosovo</option>\n" +
            "<option value=\"KWT_Kuwait_2\">Kuwait</option>\n" +
            "<option value=\"KGZ_Kyrgyzstan_3\">Kyrgyzstan</option>\n" +
            "<option value=\"LAO_Laos_3\">Laos</option>\n" +
            "<option value=\"LVA_Latvia_3\">Latvia</option>\n" +
            "<option value=\"LBN_Lebanon_4\">Lebanon</option>\n" +
            "<option value=\"LSO_Lesotho_2\">Lesotho</option>\n" +
            "<option value=\"LBR_Liberia_4\">Liberia</option>\n" +
            "<option value=\"LBY_Libya_2\">Libya</option>\n" +
            "<option value=\"LIE_Liechtenstein_2\">Liechtenstein</option>\n" +
            "<option value=\"LTU_Lithuania_3\">Lithuania</option>\n" +
            "<option value=\"LUX_Luxembourg_5\">Luxembourg</option>\n" +
            "<option value=\"MAC_Macao_3\">Macao</option>\n" +
            "<option value=\"MKD_Macedonia_2\">Macedonia</option>\n" +
            "<option value=\"MDG_Madagascar_5\">Madagascar</option>\n" +
            "<option value=\"MWI_Malawi_4\">Malawi</option>\n" +
            "<option value=\"MYS_Malaysia_3\">Malaysia</option>\n" +
            "<option value=\"MDV_Maldives_1\">Maldives</option>\n" +
            "<option value=\"MLI_Mali_5\">Mali</option>\n" +
            "<option value=\"MLT_Malta_1\">Malta</option>\n" +
            "<option value=\"MHL_Marshall Islands_1\">Marshall Islands</option>\n" +
            "<option value=\"MTQ_Martinique_3\">Martinique</option>\n" +
            "<option value=\"MRT_Mauritania_3\">Mauritania</option>\n" +
            "<option value=\"MUS_Mauritius_2\">Mauritius</option>\n" +
            "<option value=\"MYT_Mayotte_2\">Mayotte</option>\n" +
            "<option value=\"MEX_Mexico_3\">Mexico</option>\n" +
            "<option value=\"FSM_Micronesia_2\">Micronesia</option>\n" +
            "<option value=\"MDA_Moldova_2\">Moldova</option>\n" +
            "<option value=\"MCO_Monaco_1\">Monaco</option>\n" +
            "<option value=\"MNG_Mongolia_3\">Mongolia</option>\n" +
            "<option value=\"MNE_Montenegro_2\">Montenegro</option>\n" +
            "<option value=\"MSR_Montserrat_2\">Montserrat</option>\n" +
            "<option value=\"MAR_Morocco_5\">Morocco</option>\n" +
            "<option value=\"MOZ_Mozambique_4\">Mozambique</option>\n" +
            "<option value=\"MMR_Myanmar_4\">Myanmar</option>\n" +
            "<option value=\"NAM_Namibia_3\">Namibia</option>\n" +
            "<option value=\"NRU_Nauru_2\">Nauru</option>\n" +
            "<option value=\"NPL_Nepal_5\">Nepal</option>\n" +
            "<option value=\"NLD_Netherlands_3\">Netherlands</option>\n" +
            "<option value=\"NCL_New Caledonia_3\">New Caledonia</option>\n" +
            "<option value=\"NZL_New Zealand_4\">New Zealand</option>\n" +
            "<option value=\"NIC_Nicaragua_3\">Nicaragua</option>\n" +
            "<option value=\"NER_Niger_4\">Niger</option>\n" +
            "<option value=\"NGA_Nigeria_3\">Nigeria</option>\n" +
            "<option value=\"NIU_Niue_1\">Niue</option>\n" +
            "<option value=\"NFK_Norfolk Island_1\">Norfolk Island</option>\n" +
            "<option value=\"PRK_North Korea_3\">North Korea</option>\n" +
            "<option value=\"XNC_Northern Cyprus_2\">Northern Cyprus</option>\n" +
            "<option value=\"MNP_Northern Mariana Islands_2\">Northern Mariana Islands</option>\n" +
            "<option value=\"NOR_Norway_3\">Norway</option>\n" +
            "<option value=\"OMN_Oman_3\">Oman</option>\n" +
            "<option value=\"PAK_Pakistan_4\">Pakistan</option>\n" +
            "<option value=\"PLW_Palau_2\">Palau</option>\n" +
            "<option value=\"PSE_Palestina_3\">Palestina</option>\n" +
            "<option value=\"PAN_Panama_4\">Panama</option>\n" +
            "<option value=\"PNG_Papua New Guinea_3\">Papua New Guinea</option>\n" +
            "<option value=\"PRY_Paraguay_3\">Paraguay</option>\n" +
            "<option value=\"PER_Peru_4\">Peru</option>\n" +
            "<option value=\"PHL_Philippines_4\">Philippines</option>\n" +
            "<option value=\"PCN_Pitcairn Islands_1\">Pitcairn Islands</option>\n" +
            "<option value=\"POL_Poland_3\">Poland</option>\n" +
            "<option value=\"PRT_Portugal_4\">Portugal</option>\n" +
            "<option value=\"PRI_Puerto Rico_2\">Puerto Rico</option>\n" +
            "<option value=\"QAT_Qatar_2\">Qatar</option>\n" +
            "<option value=\"COG_Republic of Congo_3\">Republic of Congo</option>\n" +
            "<option value=\"REU_Reunion_3\">Reunion</option>\n" +
            "<option value=\"ROU_Romania_3\">Romania</option>\n" +
            "<option value=\"RUS_Russia_4\">Russia</option>\n" +
            "<option value=\"RWA_Rwanda_6\">Rwanda</option>\n" +
            "<option value=\"BLM_Saint-Barthélemy_1\">Saint-Barthélemy</option>\n" +
            "<option value=\"MAF_Saint-Martin_1\">Saint-Martin</option>\n" +
            "<option value=\"SHN_Saint Helena_3\">Saint Helena</option>\n" +
            "<option value=\"KNA_Saint Kitts and Nevis_2\">Saint Kitts and Nevis</option>\n" +
            "<option value=\"LCA_Saint Lucia_2\">Saint Lucia</option>\n" +
            "<option value=\"SPM_Saint Pierre and Miquelon_2\">Saint Pierre and Miquelon</option>\n" +
            "<option value=\"VCT_Saint Vincent and the Grenadines_2\">Saint Vincent and the Grenadines</option>\n" +
            "<option value=\"WSM_Samoa_3\">Samoa</option>\n" +
            "<option value=\"SMR_San Marino_2\">San Marino</option>\n" +
            "<option value=\"STP_Sao Tome and Principe_3\">Sao Tome and Principe</option>\n" +
            "<option value=\"SAU_Saudi Arabia_2\">Saudi Arabia</option>\n" +
            "<option value=\"SEN_Senegal_5\">Senegal</option>\n" +
            "<option value=\"SRB_Serbia_3\">Serbia</option>\n" +
            "<option value=\"SYC_Seychelles_2\">Seychelles</option>\n" +
            "<option value=\"SLE_Sierra Leone_4\">Sierra Leone</option>\n" +
            "<option value=\"SGP_Singapore_2\">Singapore</option>\n" +
            "<option value=\"SXM_Sint Maarten_1\">Sint Maarten</option>\n" +
            "<option value=\"SVK_Slovakia_3\">Slovakia</option>\n" +
            "<option value=\"SVN_Slovenia_3\">Slovenia</option>\n" +
            "<option value=\"SLB_Solomon Islands_3\">Solomon Islands</option>\n" +
            "<option value=\"SOM_Somalia_3\">Somalia</option>\n" +
            "<option value=\"ZAF_South Africa_5\">South Africa</option>\n" +
            "<option value=\"SGS_South Georgia and the South Sandwich Islands_1\">South Georgia and the South Sandwich Islands</option>\n" +
            "<option value=\"KOR_South Korea_3\">South Korea</option>\n" +
            "<option value=\"SSD_South Sudan_4\">South Sudan</option>\n" +
            "<option value=\"ESP_Spain_5\">Spain</option>\n" +
            "<option value=\"LKA_Sri Lanka_3\">Sri Lanka</option>\n" +
            "<option value=\"SDN_Sudan_4\">Sudan</option>\n" +
            "<option value=\"SUR_Suriname_3\">Suriname</option>\n" +
            "<option value=\"SJM_Svalbard and Jan Mayen_2\">Svalbard and Jan Mayen</option>\n" +
            "<option value=\"SWZ_Swaziland_3\">Swaziland</option>\n" +
            "<option value=\"SWE_Sweden_3\">Sweden</option>\n" +
            "<option value=\"CHE_Switzerland_4\">Switzerland</option>\n" +
            "<option value=\"SYR_Syria_3\">Syria</option>\n" +
            "<option value=\"TWN_Taiwan_3\">Taiwan</option>\n" +
            "<option value=\"TJK_Tajikistan_4\">Tajikistan</option>\n" +
            "<option value=\"TZA_Tanzania_4\">Tanzania</option>\n" +
            "<option value=\"THA_Thailand_4\">Thailand</option>\n" +
            "<option value=\"TLS_Timor-Leste_4\">Timor-Leste</option>\n" +
            "<option value=\"TGO_Togo_3\">Togo</option>\n" +
            "<option value=\"TKL_Tokelau_2\">Tokelau</option>\n" +
            "<option value=\"TON_Tonga_2\">Tonga</option>\n" +
            "<option value=\"TTO_Trinidad and Tobago_2\">Trinidad and Tobago</option>\n" +
            "<option value=\"TUN_Tunisia_3\">Tunisia</option>\n" +
            "<option value=\"TUR_Turkey_3\">Turkey</option>\n" +
            "<option value=\"TKM_Turkmenistan_2\">Turkmenistan</option>\n" +
            "<option value=\"TCA_Turks and Caicos Islands_2\">Turks and Caicos Islands</option>\n" +
            "<option value=\"TUV_Tuvalu_2\">Tuvalu</option>\n" +
            "<option value=\"UGA_Uganda_5\">Uganda</option>\n" +
            "<option value=\"UKR_Ukraine_3\">Ukraine</option>\n" +
            "<option value=\"ARE_United Arab Emirates_2\">United Arab Emirates</option>\n" +
            "<option value=\"GBR_United Kingdom_3\">United Kingdom</option>\n" +
            "<option value=\"USA_United States_3\">United States</option>\n" +
            "<option value=\"UMI_United States Minor Outlying Islands_2\">United States Minor Outlying Islands</option>\n" +
            "<option value=\"URY_Uruguay_3\">Uruguay</option>\n" +
            "<option value=\"UZB_Uzbekistan_3\">Uzbekistan</option>\n" +
            "<option value=\"VUT_Vanuatu_3\">Vanuatu</option>\n" +
            "<option value=\"VAT_Vatican City_1\">Vatican City</option>\n" +
            "<option value=\"VEN_Venezuela_3\">Venezuela</option>\n" +
            "<option value=\"VNM_Vietnam_4\">Vietnam</option>\n" +
            "<option value=\"VIR_Virgin Islands, U.S._3\">Virgin Islands, U.S.</option>\n" +
            "<option value=\"WLF_Wallis and Futuna_3\">Wallis and Futuna</option>\n" +
            "<option value=\"ESH_Western Sahara_2\">Western Sahara</option>\n" +
            "<option value=\"YEM_Yemen_3\">Yemen</option>\n" +
            "<option value=\"ZMB_Zambia_3\">Zambia</option>\n" +
            "<option value=\"ZWE_Zimbabwe_3\">Zimbabwe</option>\n" +
            "\n" +
            "\n" +
            "  </select>";



    private Site site = Site.me().setRetryTimes(3).setSleepTime(100).setTimeOut(1000000);



    public static HashMap<String,String>  hashMap=new HashMap<String, String>();


    public void process(Page page) {

        List<Selectable> countryList=page.getHtml().xpath("//select[@name='cnt']//option").nodes();


        String b="";
        String c="";

        for(Selectable a:countryList)
        {
            b=a.xpath("//option/text()").toString();
            b=b.replace(" ","_");
//            System.out.println(b);
            c=a.xpath("//option/@value").toString();
            System.out.println(b+"|"+c);

//            hashMap.put(b,c);
            b="";
            c="";

        }




    }

    public Site getSite() {
        return site;
    }




    public static void main(String[] args) {
        String url="http://gadm.org/country";

        Spider.create(new ListPageProcessor()).addUrl(url).thread(10).run();
    }


}
