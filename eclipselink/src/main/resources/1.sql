SELECT    evaluation0
FROM      com.tosan.bpms.model.sql.evaluation Evaluation0
LEFT JOINFETCH     evaluation0.file1 file1
LEFT JOINFETCH evaluation0.file2 file2
JOINFETCH evaluation0.reception reception3
JOINFETCH reception3.portfolio portfolio4
JOINFETCH     portfolio4.portfoliolicenses portfoliolicense5
LEFT JOINFETCH portfoliolicense5.FILE file7
JOINFETCH portfoliolicense5.licensedetails licensedetails8
JOINFETCH     licensedetails8.portfoliolicense portfoliolicense9
LEFT JOINFETCH     portfoliolicense9.FILE file11
LEFT JOINFETCH licensedetails8.FILE file13
JOINFETCH     portfolio4.portfoliolocations portfoliolocation14
LEFT JOINFETCH portfoliolocation14.FILE file16
JOINFETCH portfoliolocation14.locationdetails locationdetails17
JOINFETCH     locationdetails17.portfoliolocation portfoliolocation18
LEFT JOINFETCH     portfoliolocation18.FILE file20
LEFT JOINFETCH locationdetails17.FILE file22
JOINFETCH    portfolio4.receptions reception23
WHERE    1=1
AND      (
                  file1.extension = :File1extension0
         AND      ( (
                                    portfoliolicense5.portfoliolicenseid = :PortfolioLicense5portfolioLicenseId0
                           AND      file7.id <= :File7id0
                           AND      file7.id != '203'
                           AND      ( (
                                                      licensedetails8.licensedetailid = :LicenseDetails8licenseDetailId0
                                             AND      portfoliolicense9.portfoliolicenseid = :PortfolioLicense9portfolioLicenseId0
                                             AND      file11.id = :File11id0
                                             AND      portfoliolicense9.licensetype = :PortfolioLicense9licenseType0
                                             AND      file13.id = :File13id0
                                             AND      licensedetails8.address.addressline1 = :LicenseDetails8addressaddressLine10 )
                                    OR       (
                                                      licensedetails8.licensedetailid = :LicenseDetails8licenseDetailId0
                                             AND      portfoliolicense9.portfoliolicenseid = :PortfolioLicense9portfolioLicenseId0
                                             AND      file11.id = :File11id0
                                             AND      portfoliolicense9.licensetype = :PortfolioLicense9licenseType0
                                             AND      file13.id = :File13id0
                                             AND      licensedetails8.address.addressline1 = :LicenseDetails8addressaddressLine10 ) )
                           AND      portfoliolicense5.licensetype = :PortfolioLicense5licenseType0 )
                  OR       (
                                    portfoliolicense5.portfoliolicenseid = :PortfolioLicense5portfolioLicenseId0
                           AND      file7.id <= :File7id0
                           AND      file7.id != '203'
                           AND      ( (
                                                      licensedetails8.licensedetailid = :LicenseDetails8licenseDetailId0
                                             AND      portfoliolicense9.portfoliolicenseid = :PortfolioLicense9portfolioLicenseId0
                                             AND      file11.id = :File11id0
                                             AND      portfoliolicense9.licensetype = :PortfolioLicense9licenseType0
                                             AND      file13.id = :File13id0
                                             AND      licensedetails8.address.addressline1 = :LicenseDetails8addressaddressLine10 )
                                    OR       (
                                                      licensedetails8.licensedetailid = :LicenseDetails8licenseDetailId0
                                             AND      portfoliolicense9.portfoliolicenseid = :PortfolioLicense9portfolioLicenseId0
                                             AND      file11.id = :File11id0
                                             AND      portfoliolicense9.licensetype = :PortfolioLicense9licenseType0
                                             AND      file13.id = :File13id0
                                             AND      licensedetails8.address.addressline1 = :LicenseDetails8addressaddressLine10 ) )
                           AND      portfoliolicense5.licensetype = :PortfolioLicense5licenseType0 ) )
         AND      ( ( (
                                             locationdetails17.locationdetailid = :LocationDetails17locationDetailId0
                                    AND      file22.id = :File22id0
                                    AND      locationdetails17.address.addressline1 != :myAddress )
                           OR       (
                                             locationdetails17.locationdetailid = :LocationDetails17locationDetailId0
                                    AND      file22.id = :File22id0
                                    AND      locationdetails17.address.addressline1 != :myAddress ) )
                  AND      portfoliolocation14.province = :PortfolioLocation14province0
                  AND      portfoliolocation14.city = :PortfolioLocation14city0
                  AND      portfoliolocation14.indstrltwn = :PortfolioLocation14indstrltwn0
                  AND      portfoliolocation14.address = :PortfolioLocation14address0
                  AND      portfoliolocation14.stablestatustype = :PortfolioLocation14stableStatusType0
                  AND      portfoliolocation14.ownerstatustype = :PortfolioLocation14ownerStatusType0
                  AND      portfoliolocation14.regionalstatustype = :PortfolioLocation14regionalStatusType0 )
         AND      ( (
                                    reception23.externalresource = :Reception23externalResource0
                           AND      reception23.branch LIKE :Reception23branch0
                           AND      reception23.equation = :Reception23equation0
                           AND      reception23.irrestimate = :Reception23irrEstimate0
                           AND      reception23.headpoint = :Reception23headPoint0
                           AND      reception23.cusprepayamnt = :Reception23cusPrePayAmnt0 )
                  OR       (
                                    reception23.externalresource = :Reception23externalResource0
                           AND      reception23.branch LIKE :Reception23branch0
                           AND      reception23.equation = :Reception23equation0
                           AND      reception23.irrestimate = :Reception23irrEstimate0
                           AND      reception23.headpoint = :Reception23headPoint0
                           AND      reception23.cusprepayamnt = :Reception23cusPrePayAmnt0 ) )
         AND      portfolio4.address.addressline1 = :Portfolio4addressaddressLine10
         AND      reception3.externalresource = :Reception3externalResource0
         AND      reception3.branch LIKE :Reception3branch0
         AND      reception3.equation = :Reception3equation0
         AND      reception3.irrestimate = :Reception3irrEstimate0
         AND      reception3.headpoint = :Reception3headPoint0
         AND      reception3.cusprepayamnt = :Reception3cusPrePayAmnt0 )
OR       reception3.receptionno > 0
OR       portfolio4.portfolioid != 0
AND      portfolio4.economictype > 0
GROUP BY reception3.receptionno ,
         portfolio4.portfolioid
HAVING   count( reception3.receptionno ) > 0
AND      count(portfolio4.portfolioid) > 0
OR       count(portfolio4.economictype) > 0
ORDER BY reception3.receptionno DESC ,
         portfolio4.portfolioid


************ Variables ************
PortfolioLicense9portfolioLicenseId0 : 34
PortfolioLocation14stableStatusType0 : 0
LicenseDetails8addressaddressLine10 : 'line1'
Reception23headPoint0 : 0
Reception23externalResource0 : false
myAddress : 'usa'
PortfolioLocation14indstrltwn0 : ''
LocationDetails17locationDetailId0 : 12
Reception3headPoint0 : 0
File7id0 : '12'
PortfolioLocation14city0 : 'rasht'
Reception3irrEstimate0 : 0
Reception23irrEstimate0 : 0
Reception3cusPrePayAmnt0 : 0
PortfolioLocation14province0 : ''
File13id0 : '12'
PortfolioLicense5licenseType0 : 0
File1extension0 : 'jpg'
Reception23equation0 : 0
Reception3branch0 : '%123%'
PortfolioLicense9licenseType0 : 0
Portfolio4addressaddressLine10 : 'line1'
File11id0 : '12'
Reception3externalResource0 : false
PortfolioLicense5portfolioLicenseId0 : 34
LicenseDetails8licenseDetailId0 : 15
PortfolioLocation14address0 : ''
PortfolioLocation14regionalStatusType0 : 0
Reception23branch0 : '%123%'
File22id0 : '12'
PortfolioLocation14ownerStatusType0 : 0
Reception3equation0 : 0
Reception23cusPrePayAmnt0 : 0
************ Variables ************