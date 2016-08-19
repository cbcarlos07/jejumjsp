/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import beans.Jejum;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CARLOS
 */
public class Jejum_DAO {
    String sql;
    ResultSet rs;
    PreparedStatement pstmt;
    Connection con;
    List lista;
    public List<Jejum> lista(){
        Jejum jejum = null;
        con = ConnectionFactory.getConnection();
        try {
            sql = "SELECT A.NM_PACIENTE     PACIENTE   " +
"                                    ,UNID_INT UNIDADE   " +
"                                    ,LEITO   " +
"                                    ,SUBSTR(A.ITEM,4)            JEJUM   " +
"                                    ,to_CHAR(A.DATA_PRESC,'DD/MM/YYYY HH24:MI:SS')         INICIO_JEJUM   " +
"                                    ,to_char(A.OBSERVACAO) OBSERVACAO   " +
"                                             ,CASE    " +
"                                                   WHEN (A.ITEM) = '10||INICIAR JEJUM ULTRASSONOGRAFIA' AND (SYSDATE-DATA_PRESC) <= 0.1666666666 " +
"                                                     THEN 'G'   " +
"                                                   WHEN (A.ITEM) = '10||INICIAR JEJUM ULTRASSONOGRAFIA' AND (SYSDATE-DATA_PRESC) BETWEEN 0.1666666666 AND 0.2083333333   " +
"                                                     THEN 'Y'  " +
"                                                   WHEN (A.ITEM) = '10||INICIAR JEJUM ULTRASSONOGRAFIA' AND (SYSDATE-DATA_PRESC) > 0.2083333333   " +
"                                                     THEN 'R'    " +
"                                                   WHEN (A.ITEM) = '9||INICIAR JEJUM TOMOGRAFIA COMPUTADORIZADA' AND (SYSDATE-DATA_PRESC) <= 0.29166666666666  " +
"                                                     THEN 'G'   " +
"                                                   WHEN (A.ITEM) = '9||INICIAR JEJUM TOMOGRAFIA COMPUTADORIZADA' AND (SYSDATE-DATA_PRESC) BETWEEN 0.29166666666666 AND 0.33333333333333 " +
"                                                     THEN 'Y'   " +
"                                                   WHEN (A.ITEM) = '9||INICIAR JEJUM TOMOGRAFIA COMPUTADORIZADA' AND (SYSDATE-DATA_PRESC) > 0.33333333333333    " +
"                                                     THEN 'R'   " +
"                                                   WHEN (A.ITEM) = '8||INICIAR JEJUM RESSONANCIA MAGNETICA' AND (SYSDATE-DATA_PRESC) <= 0.29166666666666    " +
"                                                     THEN 'G'   " +
"                                                   WHEN (A.ITEM) = '8||INICIAR JEJUM RESSONANCIA MAGNETICA' AND (SYSDATE-DATA_PRESC) BETWEEN 0.29166666666666 AND 0.33333333333333    " +
"                                                     THEN 'Y'   " +
"                                                   WHEN (A.ITEM) = '8||INICIAR JEJUM RESSONANCIA MAGNETICA' AND (SYSDATE-DATA_PRESC) > 0.33333333333333    " +
"                                                     THEN 'R'   " +
"                                                   WHEN (A.ITEM) = '7||INICIAR JEJUM RADIOLOGIA VASCULAR'  AND (SYSDATE-DATA_PRESC) <= 0.5416666666666    " +
"                                                     THEN 'G'   " +
"                                                   WHEN (A.ITEM) = '7||INICIAR JEJUM RADIOLOGIA VASCULAR'  AND (SYSDATE-DATA_PRESC) BETWEEN 0.5416666666666 AND 0.58333333333333    " +
"                                                     THEN 'A'   " +
"                                                   WHEN (A.ITEM) = '7||INICIAR JEJUM RADIOLOGIA VASCULAR'  AND (SYSDATE-DATA_PRESC) > 0.58333333333333    " +
"                                                     THEN 'R'   " +
"                                                   WHEN (A.ITEM) = '1||INICIAR JEJUM COLANGIORESSONANCIA'  AND (SYSDATE-DATA_PRESC) <= 0.5416666666666    " +
"                                                     THEN 'G'   " +
"                                                   WHEN (A.ITEM) = '1||INICIAR JEJUM COLANGIORESSONANCIA'  AND (SYSDATE-DATA_PRESC) BETWEEN 0.5416666666666 AND 0.58333333333333    " +
"                                                     THEN 'Y'   " +
"                                                   WHEN (A.ITEM) = '1||INICIAR JEJUM COLANGIORESSONANCIA'  AND (SYSDATE-DATA_PRESC) > 0.58333333333333    " +
"                                                     THEN 'R'   " +
"                                                   WHEN (A.ITEM) = '2||INICIAR JEJUM COLONOSCOPIA'  AND (SYSDATE-DATA_PRESC) <= 0.416666666666    " +
"                                                     THEN 'G'   " +
"                                                   WHEN (A.ITEM) = '2||INICIAR JEJUM COLONOSCOPIA'  AND (SYSDATE-DATA_PRESC) BETWEEN 0.416666666666 AND 0.458333333333333    " +
"                                                     THEN 'Y'   " +
"                                                   WHEN (A.ITEM) = '2||INICIAR JEJUM COLONOSCOPIA'  AND (SYSDATE-DATA_PRESC) > 0.458333333333333    " +
"                                                     THEN 'R'   " +
"                                                   WHEN (A.ITEM) = '3||INICIAR JEJUM ENDOSCOPIA'   AND (SYSDATE-DATA_PRESC) <= 0.416666666666    " +
"                                                     THEN 'G'   " +
"                                                   WHEN (A.ITEM) = '3||INICIAR JEJUM ENDOSCOPIA'   AND (SYSDATE-DATA_PRESC) BETWEEN 0.416666666666 AND 0.458333333333333    " +
"                                                     THEN 'Y'   " +
"                                                   WHEN (A.ITEM) = '3||INICIAR JEJUM ENDOSCOPIA'   AND (SYSDATE-DATA_PRESC) > 0.458333333333333    " +
"                                                     THEN 'R'   " +
"                                                   WHEN (A.ITEM) = '5||INICIAR JEJUM PROCEDIMENTO CIRURGICO (OUTROS)'   AND (SYSDATE-DATA_PRESC) <= 0.583333333333   " +
"                                                     THEN 'G'   " +
"                                                   WHEN (A.ITEM) = '5||INICIAR JEJUM PROCEDIMENTO CIRURGICO (OUTROS)'   AND (SYSDATE-DATA_PRESC) BETWEEN 0.583333333333 AND 0.625    " +
"                                                     THEN 'Y'   " +
"                                                   WHEN (A.ITEM) = '5||INICIAR JEJUM PROCEDIMENTO CIRURGICO (OUTROS)'   AND (SYSDATE-DATA_PRESC) > 0.625    " +
"                                                     THEN 'R'   " +
"                                                   WHEN (A.ITEM) = '6||INICIAR JEJUM PROCEDIMENTO CIRURGICO DO TRATO GASTRO'  AND (SYSDATE-DATA_PRESC) <= 1    " +
"                                                     THEN 'G'   " +
"                                                   WHEN (A.ITEM) = '6||INICIAR JEJUM PROCEDIMENTO CIRURGICO DO TRATO GASTRO'  AND (SYSDATE-DATA_PRESC) BETWEEN 1 AND 1.083333333    " +
"                                                     THEN 'Y'   " +
"                                                   WHEN (A.ITEM) = '6||INICIAR JEJUM PROCEDIMENTO CIRURGICO DO TRATO GASTRO'  AND (SYSDATE-DATA_PRESC) > 1.0833333333    " +
"                                                     THEN 'R'   " +
"                                                   WHEN (A.ITEM) = '4||INICIAR JEJUM OUTROS MOTIVOS'  AND (SYSDATE-DATA_PRESC) <= 0.5416666666666    " +
"                                                     THEN 'G'   " +
"                                                   WHEN (A.ITEM) = '4||INICIAR JEJUM OUTROS MOTIVOS'  AND (SYSDATE-DATA_PRESC) BETWEEN 0.5416666666666 AND 0.58333333333333    " +
"                                                     THEN 'Y'   " +
"                                                   WHEN (A.ITEM) = '4||INICIAR JEJUM OUTROS MOTIVOS'  AND (SYSDATE-DATA_PRESC) > 0.58333333333333    " +
"                                                     THEN 'R'     " +
"                                                   WHEN (A.ITEM) = '11||INICIAR JEJUM PROCEDIMENTO CIRURGICO - CIRURGIA GERAL'   AND (SYSDATE-DATA_PRESC) <= 0.583333333333   " +
"                                                     THEN 'G'   " +
"                                                   WHEN (A.ITEM) = '11||INICIAR JEJUM PROCEDIMENTO CIRURGICO - CIRURGIA GERAL'   AND (SYSDATE-DATA_PRESC) BETWEEN 0.583333333333 AND 0.625    " +
"                                                     THEN 'Y'   " +
"                                                   WHEN (A.ITEM) = '11||INICIAR JEJUM PROCEDIMENTO CIRURGICO - CIRURGIA GERAL'   AND (SYSDATE-DATA_PRESC) > 0.625    " +
"                                                     THEN 'R'    " +
"                                                   WHEN (A.ITEM) = '12||INICIAR JEJUM PROCEDIMENTO CIRURGICO - ORTOPEDIA'   AND (SYSDATE-DATA_PRESC) <= 0.583333333333   " +
"                                                     THEN 'G'   " +
"                                                   WHEN (A.ITEM) = '12||INICIAR JEJUM PROCEDIMENTO CIRURGICO - ORTOPEDIA'   AND (SYSDATE-DATA_PRESC) BETWEEN 0.583333333333 AND 0.625    " +
"                                                     THEN 'Y'   " +
"                                                   WHEN (A.ITEM) = '12||INICIAR JEJUM PROCEDIMENTO CIRURGICO - ORTOPEDIA'   AND (SYSDATE-DATA_PRESC) > 0.625    " +
"                                                     THEN 'R'    " +
"                                                   WHEN (A.ITEM) = '13||INICIAR JEJUM PROCEDIMENTO CIRURGICO - CARDIOLOGIA'   AND (SYSDATE-DATA_PRESC) <= 0.583333333333   " +
"                                                     THEN 'G'   " +
"                                                   WHEN (A.ITEM) = '13||INICIAR JEJUM PROCEDIMENTO CIRURGICO - CARDIOLOGIA'   AND (SYSDATE-DATA_PRESC) BETWEEN 0.583333333333 AND 0.625    " +
"                                                     THEN 'Y'   " +
"                                                   WHEN (A.ITEM) = '13||INICIAR JEJUM PROCEDIMENTO CIRURGICO - CARDIOLOGIA'   AND (SYSDATE-DATA_PRESC) > 0.625    " +
"                                                     THEN 'R'    " +
"                                                   WHEN (A.ITEM) = '16||INICIAR JEJUM PROCEDIMENTO CIRURGICO - UROLOGIA'   AND (SYSDATE-DATA_PRESC) <= 0.583333333333   " +
"                                                     THEN 'G'   " +
"                                                   WHEN (A.ITEM) = '16||INICIAR JEJUM PROCEDIMENTO CIRURGICO - UROLOGIA'   AND (SYSDATE-DATA_PRESC) BETWEEN 0.583333333333 AND 0.625    " +
"                                                     THEN 'Y'   " +
"                                                   WHEN (A.ITEM) = '16||INICIAR JEJUM PROCEDIMENTO CIRURGICO - UROLOGIA'   AND (SYSDATE-DATA_PRESC) > 0.625    " +
"                                                     THEN 'R'    " +
"                                                   WHEN (A.ITEM) = '15||INICIAR JEJUM PROCEDIMENTO CIRURGICO - GINECOLOGIA'   AND (SYSDATE-DATA_PRESC) <= 0.583333333333   " +
"                                                     THEN 'G'   " +
"                                                   WHEN (A.ITEM) = '15||INICIAR JEJUM PROCEDIMENTO CIRURGICO - GINECOLOGIA'   AND (SYSDATE-DATA_PRESC) BETWEEN 0.583333333333 AND 0.625    " +
"                                                     THEN 'Y'   " +
"                                                   WHEN (A.ITEM) = '15||INICIAR JEJUM PROCEDIMENTO CIRURGICO - GINECOLOGIA'   AND (SYSDATE-DATA_PRESC) > 0.625    " +
"                                                     THEN 'R'    " +
"                                                   WHEN (A.ITEM) = '14||INICIAR JEJUM PROCEDIMENTO CIRURGICO - NEUROLOGIA'   AND (SYSDATE-DATA_PRESC) <= 0.583333333333   " +
"                                                     THEN 'G'   " +
"                                                   WHEN (A.ITEM) = '14||INICIAR JEJUM PROCEDIMENTO CIRURGICO - NEUROLOGIA'   AND (SYSDATE-DATA_PRESC) BETWEEN 0.583333333333 AND 0.625    " +
"                                                     THEN 'Y'   " +
"                                                   WHEN (A.ITEM) = '14||INICIAR JEJUM PROCEDIMENTO CIRURGICO - NEUROLOGIA'   AND (SYSDATE-DATA_PRESC) > 0.625    " +
"                                                     THEN 'R'    " +
"                                                   WHEN (A.ITEM) = '17||INICIAR JEJUM PROCEDIMENTO CIRURGICO - BUCOMAXILO'   AND (SYSDATE-DATA_PRESC) <= 0.583333333333   " +
"                                                     THEN 'G'   " +
"                                                   WHEN (A.ITEM) = '17||INICIAR JEJUM PROCEDIMENTO CIRURGICO - BUCOMAXILO'   AND (SYSDATE-DATA_PRESC) BETWEEN 0.583333333333 AND 0.625    " +
"                                                     THEN 'Y'   " +
"                                                   WHEN (A.ITEM) = '17||INICIAR JEJUM PROCEDIMENTO CIRURGICO - BUCOMAXILO'   AND (SYSDATE-DATA_PRESC) > 0.625    " +
"                                                     THEN 'R'    " +
"                                               END SITUACAO   " +
"                                FROM (   " +
"                                   select A.NM_PACIENTE   " +
"                                         ,(TO_DATE(A.DATA||' '||B.HORA||' '||(SELECT CASE WHEN B.HORA = 00 AND MINUTO = 00 THEN '01' ELSE MINUTO END FROM DUAL),'DD/MM/YYYY HH24:MI:SS')) DATA_PRESC   " +
"                                         ,ITEM   " +
"                                         ,A.CRIACAO   " +
"                                         ,E.OBSERVACAO   " +
"                                         ,UNID_INT   " +
"                                         ,LEITO   " +
"                                     from (     " +
"                                                       SELECT    p.nm_paciente   " +
"                                                               ,MAX(DBMS_LOB.substr(lo_valor))  KEEP (DENSE_RANK LAST ORDER BY E.DH_CRIACAO) DATA   " +
"                                                               ,MAX(a.cd_documento_clinico) KEEP (DENSE_RANK LAST ORDER BY E.DH_CRIACAO) CD_DOCUMENTO_CLINICO   " +
"                                                               ,MAX(E.DH_CRIACAO)              CRIACAO   " +
"                                                               ,MAX(U.DS_UNID_INT)             UNID_INT   " +
"                                                               ,(L.DS_RESUMO)               LEITO   " +
"                                                          FROM  pw_editor_clinico           a   " +
"                                                               ,dbamv.editor_documento      b    " +
"                                                               ,editor_registro_campo       c   " +
"                                                               ,editor_campo                d   " +
"                                                               ,dbamv.pw_documento_clinico  e   " +
"                                                               ,atendime f   " +
"                                                               ,paciente p   " +
"                                                               ,leito    l   " +
"                                                               ,unid_int u   " +
"                                                         WHERE  f.cd_paciente              =     p.cd_paciente   " +
"                                                           and  a.cd_documento             =     b.cd_documento   " +
"                                                           AND  a.cd_editor_registro       =     c.cd_registro(+)   " +
"                                                           AND  c.cd_campo                 =     d.cd_campo(+)   " +
"                                                           and  f.cd_atendimento           =     e.cd_atendimento   " +
"                                                           AND  a.cd_documento_clinico     =     e.cd_documento_clinico   " +
"                                                           and  f.cd_leito                 =     l.cd_leito   " +
"                                                           and  u.cd_unid_int              =     l.cd_unid_int   " +
"                                                           and  e.tp_status                =     'FECHADO'   " +
"                                                           and  a.cd_documento             =     195   " +
"                                                           and  d.cd_metadado              in (63512)   " +
"                                                           GROUP BY P.NM_PACIENTE,L.DS_RESUMO  " +
"                                             ) a   " +
"                                             ,(   " +
"                                              SELECT  p.nm_paciente   " +
"                                                               ,DBMS_LOB.substr(lo_valor) HORA   " +
"                                                               ,a.cd_documento_clinico   " +
"                                                          FROM  pw_editor_clinico           a   " +
"                                                               ,dbamv.editor_documento      b    " +
"                                                               ,editor_registro_campo       c   " +
"                                                               ,editor_campo                d   " +
"                                                               ,dbamv.pw_documento_clinico  e   " +
"                                                               ,atendime f   " +
"                                                               ,paciente p   " +
"                                                         WHERE  f.cd_paciente              =     p.cd_paciente   " +
"                                                           and  a.cd_documento             =     b.cd_documento   " +
"                                                           AND  a.cd_editor_registro       =     c.cd_registro(+)   " +
"                                                           AND  c.cd_campo                 =     d.cd_campo(+)   " +
"                                                           and  f.cd_atendimento           =     e.cd_atendimento   " +
"                                                           AND  a.cd_documento_clinico     =     e.cd_documento_clinico   " +
"                                                           and  e.tp_status                =     'FECHADO'   " +
"                                                           and  a.cd_documento             =     195   " +
"                                                           and  d.cd_metadado              in (63513)   " +
"                                             ) b   " +
"                                             ,(   " +
"                                              SELECT  p.nm_paciente   " +
"                                                               ,DBMS_LOB.substr(lo_valor) minuto   " +
"                                                               ,a.cd_documento_clinico   " +
"                                                          FROM  pw_editor_clinico           a   " +
"                                                               ,dbamv.editor_documento      b    " +
"                                                               ,editor_registro_campo       c   " +
"                                                               ,editor_campo                d   " +
"                                                               ,dbamv.pw_documento_clinico  e   " +
"                                                               ,atendime f   " +
"                                                               ,paciente p   " +
"                                                         WHERE  f.cd_paciente              =     p.cd_paciente   " +
"                                                           and  a.cd_documento             =     b.cd_documento   " +
"                                                           AND  a.cd_editor_registro       =     c.cd_registro(+)   " +
"                                                           AND  c.cd_campo                 =     d.cd_campo(+)   " +
"                                                           and  f.cd_atendimento           =     e.cd_atendimento   " +
"                                                           AND  a.cd_documento_clinico     =     e.cd_documento_clinico   " +
"                                                           and  e.tp_status                =     'FECHADO'   " +
"                                                           and  a.cd_documento             =     195   " +
"                                                           and  d.cd_metadado              in (63515)   " +
"                                             ) c   " +
"                                             ,(   " +
"                                              SELECT  p.nm_paciente   " +
"                                                               ,DBMS_LOB.substr(lo_valor) ITEM   " +
"                                                               ,a.cd_documento_clinico   " +
"                                                          FROM  pw_editor_clinico           a   " +
"                                                               ,dbamv.editor_documento      b    " +
"                                                               ,editor_registro_campo       c   " +
"                                                               ,editor_campo                d   " +
"                                                               ,dbamv.pw_documento_clinico  e   " +
"                                                               ,atendime f   " +
"                                                               ,paciente p   " +
"                                                         WHERE  f.cd_paciente              =     p.cd_paciente   " +
"                                                           and  a.cd_documento             =     b.cd_documento   " +
"                                                           AND  a.cd_editor_registro       =     c.cd_registro(+)   " +
"                                                           AND  c.cd_campo                 =     d.cd_campo(+)   " +
"                                                           and  f.cd_atendimento           =     e.cd_atendimento   " +
"                                                           AND  a.cd_documento_clinico     =     e.cd_documento_clinico   " +
"                                                           and  e.tp_status                =     'FECHADO'   " +
"                                                           and  a.cd_documento             =     195   " +
"                                                           and  e.dh_criacao              >=     sysdate-2   " +
"                                                           and  d.cd_metadado              in (63514)   " +
"                                                         " +
"                                             ) d   " +
"                                              ,(   " +
"                                              SELECT  p.nm_paciente   " +
"                                                               ,DBMS_LOB.substr(lo_valor) OBSERVACAO   " +
"                                                               ,a.cd_documento_clinico   " +
"                                                          FROM  pw_editor_clinico           a   " +
"                                                               ,dbamv.editor_documento      b    " +
"                                                               ,editor_registro_campo       c   " +
"                                                               ,editor_campo                d   " +
"                                                               ,dbamv.pw_documento_clinico  e   " +
"                                                               ,atendime f   " +
"                                                               ,paciente p   " +
"                                                         WHERE  f.cd_paciente              =     p.cd_paciente   " +
"                                                           and  a.cd_documento             =     b.cd_documento   " +
"                                                           AND  a.cd_editor_registro       =     c.cd_registro(+)   " +
"                                                           AND  c.cd_campo                 =     d.cd_campo(+)   " +
"                                                           and  f.cd_atendimento           =     e.cd_atendimento   " +
"                                                           AND  a.cd_documento_clinico     =     e.cd_documento_clinico   " +
"                                                           and  e.tp_status                =     'FECHADO'   " +
"                                                           and  a.cd_documento             =     195   " +
"                                                           and  e.dh_criacao              >=     sysdate-2   " +
"                                                           and  d.cd_metadado              in (63511)   " +
"                                             ) e   " +
"                                        WHERE A.NM_PACIENTE = B.NM_PACIENTE   " +
"                                          AND A.NM_PACIENTE = C.NM_PACIENTE   " +
"                                          AND A.NM_PACIENTE = D.NM_PACIENTE   " +
"                                          AND A.NM_PACIENTE = E.NM_PACIENTE   " +
"                                          and a.cd_documento_clinico = b.cd_documento_clinico   " +
"                                          AND a.cd_documento_clinico = C.CD_DOCUMENTO_CLINICO   " +
"                                          AND a.cd_documento_clinico = D.CD_DOCUMENTO_CLINICO   " +
"                                          AND a.cd_documento_clinico = e.cd_documento_clinico   " +
"                                    ) A   " +
"                                    ,(   " +
"                                         SELECT  P.NM_PACIENTE   " +
"                                                ,MAX(TP.DS_TIP_PRESC) ITEM   " +
"                                                ,MAX(TP.CD_TIP_PRESC) CD_ITEM   " +
"                                                ,MAX(IPM.DH_INICIAL) DATA   " +
"                                           FROM  PRE_MED   PM   " +
"                                                ,ITPRE_MED IPM   " +
"                                                ,DBAMV.TIP_PRESC TP   " +
"                                                ,DBAMV.ATENDIME  A   " +
"                                                ,DBAMV.PACIENTE  P   " +
"                                          WHERE  PM.CD_PRE_MED    = IPM.CD_PRE_MED   " +
"                                            AND TP.CD_TIP_PRESC   = IPM.CD_TIP_PRESC   " +
"                                            AND PM.CD_ATENDIMENTO = A.CD_ATENDIMENTO   " +
"                                            AND A.CD_PACIENTE     = P.CD_PACIENTE   " +
"                                            AND PM.DH_CRIACAO    >= sysdate-2   " +
"                                            AND TP.CD_TIP_PRESC   IN  (13662,14286)   " +
"                                            GROUP BY P.NM_PACIENTE   " +
"                                            ORDER BY 2 DESC   " +
"                                    ) B   " +
"                                    WHERE A.NM_PACIENTE = B.NM_PACIENTE(+)   " +
"                                      AND (B.CD_ITEM IS NULL OR A.DATA_PRESC > B.DATA)   " +
"                                 ORDER BY 5";
            pstmt = con.prepareStatement(sql);
            
            rs = pstmt.executeQuery();
            lista = new ArrayList();
            while(rs.next()){
                jejum = new Jejum();
                jejum.setPaciente(rs.getString("PACIENTE"));
                jejum.setUnidade(rs.getString("UNIDADE"));
                jejum.setLeito(rs.getString("LEITO"));
                jejum.setJejum(rs.getString("JEJUM").replace("|", ""));
                jejum.setInicio_Jejum(rs.getString("INICIO_JEJUM"));
                jejum.setObs(rs.getString("OBSERVACAO"));
                jejum.setSituacao(rs.getString("SITUACAO"));
                
                lista.add(jejum);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Jejum_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
}
