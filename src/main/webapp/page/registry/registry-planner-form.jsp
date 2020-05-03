            <form:form commandName="registryWishForm" action="${pageContext.request.contextPath}/registry-mutual-funds/registryFunds.do" method="POST">
                <!-- <form [formGroup]="registryForm"> -->
                
                <div>
                	<form:hidden path="wishType"/>
                	<form:hidden path="schemeId"/>
                	
                </div>
                <div class="row">
                    <div class="col-md-6 col-lg-6 label_style">
                        <label>
                            <b>Investment Type
                                <sup style="color: red;">*</sup>
                            </b>
                        </label>
                    </div>
                    <div class="col-md-6 col-lg-6">
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <label class="input-group-text" for="inputGroupSelect01" style="color: red;">
                                    <i class="far fa-dot-circle"></i>
                                </label>
                            </div>
                            <form:select class="custom-select" id="investmenttype" path="investType" onchange="investmenttypeBox();">
                                <form:options items = "${investmentType}"   />
                            </form:select>	
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-6 label_style">
                        <label>
                            <b>Plan frequency
                                <sup style="color: red;">*</sup>
                            </b>
                        </label>
                    </div>
                    <div class="col-6" style="margin-left: -1px;">
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <label class="input-group-text" for="inputGroupSelect01" style="color: #2326CF;">
                                    <i class="far fa-dot-circle"></i>
                                </label>
                            </div>
                            <form:select class="custom-select" id="inputGroupSelect01" path="tenure" disabled="true">                                
						          <option value="12">Monthly</option>
						          <option value="13" selected="selected">Lumpsum</option>
                            </form:select>
                        </div>
                    </div>
                </div>
                <!-- 
                 <div class="row" id="sipt">
                    <div class="col-md-6 col-lg-6 label_style">
                        <label>
                            <b style="color: rgb(0, 194, 125);">Your desired amount</b><sup style="color: red;">*</sup>
                        </label>

                    </div>
                    <div class="col-md-6 col-lg-6">
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="basic-addon1" style="background-color: #2e58da;">
                                    <img src="<c:url value="${contextPath}/resources/images/sip.png"/>" class="img-fluid;" style="height: 25px;" />
                                </span>
                            </div>
                            <form:input type="text" id="amount_sip" pattern="[0-9]*" maxlength="5" class="form-control" placeholder="0" path="amount" aria-describedby="sizing-addon1" />
                        </div>
                    </div>
                </div>
                 -->
               
                <div class="row" id="sip">
                    <div class="col-6 label_style" >
                        <label id="sipt"  style="display: none;">
                            <b style="color: rgb(0, 194, 125);">Your desired amount</b><sup style="color: red;">*</sup>
                        </label>
                         <label id="sipm">
                            <b style="color: rgb(0, 194, 125);">Your contribution amount</b><sup style="color: red;">*</sup>
                        </label>

                    </div>
                    <div class="col-6" style="margin-left: -1px;">
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="basic-addon1" style="padding: 3px 7px;">
                                    <img src="<c:url value="${contextcdn}/resources/images/sip.png"/>" class="img-fluid;" style="height: 25px;" />
                                </span>
                            </div>
                            <form:input type="text" id="amount_sip" pattern="[0-9]*" maxlength="5" class="form-control" placeholder="0" path="amount" aria-describedby="sizing-addon1" onchange="getDate();" />
                        </div>
                    </div>
                </div>
                <!-- 
                 <div class="row" id="amountbox">
                    <div class="col-md-6 col-lg-6 label_style">
                        <label>
                            <b style="color: #fd5c5c">What is the expected expenditure?</b><sup style="color: red;">*</sup>
                        </label>
                    </div>
                    <div class="col-md-6 col-lg-6">
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="basic-addon1" style="background-color: #fdebd8;">
                                    <img src="<c:url value="${contextPath}/resources/images/loan-amount.png"/>" class="img-fluid;" style="height: 25px;" />
                                </span>
                            </div>
                            <form:input id="amount" type="text" pattern="[0-9]*" maxlength="5" class="form-control" placeholder="0" path="amount" aria-describedby="sizing-addon1" />
                        </div>
                    </div>
                </div>
                
                 -->
                

              
                <div class="row">
                    <div class="col-md-6 col-lg-6 label_style">
                        <label>
                            <b>When is the occasion?
                                <sup style="color: red;">*</sup>
                            </b>
                        </label>
                        <div><span style="font-size: 10px; color: blue; line-height: 1; margin-top: -10px;">Event plan should be between 1 month to 12 months in advance</span></div>
                    </div>
                    <div class="col-md-6 col-lg-6">
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                            	<label class="input-group-text" for="inputGroupSelect01">
                                    <i class="far fa-calendar-alt"></i>
                                 </label>
                            </div>
                            <!-- <input class="form-control" placeholder="yyyy-mm-dd" name="dp" [(ngModel)]="model" ngbDatepicker [markDisabled]="isDisabled" -->
                            <form:input type="date" path="date" class="form-control" id="eventdate" onchange="getDate();"
									placeholder="(yyyy-mm-dd)" />
							
                        </div>
                        
                        <!-- <div style="margin-bottom: 10px;">
                            You have
                            <span class="days_calc">{{dateDiff}} </span> days to go
                        </div> -->
                    </div>
                </div>
                  <div class="row">
                    <div class="col-6 label_style">
                        <label style="padding: 3px 9px;">
                            <b>Who is it for?
                                <sup style="color: red;">*</sup>
                            </b>
                        </label>
                    </div>
                    <div class="col-6" style="margin-left: -1px;">
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <label class="input-group-text" for="inputGroupSelect01">
                                    <i class="fa fa-users" aria-hidden="true"></i>
                                </label>
                            </div>
                            <form:select class="custom-select" id="inputGroupSelect01" path="person" style="padding-left: 2px;">
                                <form:options items = "${personType}" />
                            </form:select>
                        </div>
                    </div>
                </div>

                <div class="draw-line">
                    <span></span>
                </div>


                <!-- Result -->

                <div class="row">
                    <div class="col-md-6 col-lg-6">
                        <div class="row" style="margin-top: 10px;">
                            <div class="col-8 label_style" style="margin-right: -1px;">
                                <label>
                                    <b>Your event budget</b>
                                </label>
                            </div>
                            <div class="col-4" style="background-color: #fb6533;border-radius: 2px;padding-left: 2px;padding-right: 2px;">
                            	<div class="amount_dis1">
                                <i class="fas fa-rupee-sign"></i> <span id="final-installment-amount">0</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 col-lg-6">
                        <div class="row" style="margin-top: 10px;">
                            <div class="col-8 label_style" style="margin-right: -1px;">
                                <label>
                                    <b>Your Contribution (SIP)</b>
                                </label>
                            </div>
                            <div class="col-4" style="background-color: #4dd072;border-radius: 2px;padding-left: 2px;padding-right: 2px;">
                            	<div class="amount_dis1">
                                	<i class="fas fa-rupee-sign"></i> <span id="final-sip-amount">0</span>
                                 </div>
                                
                            </div>
                        </div>
                    </div>
                </div>
                <div style="display: none;"><span id="amountbox"></span></div>

                <!-- <div class="row">
                    <div class="col-md-6 col-lg-6 label_style">
                        <label>
                            <b>Do you want the product delivered?</b> (Optional)</label>
                    </div>
                    <div class="col-md-6 col-lg-6">
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" type="checkbox" value="" disabled> Deliver the product
                            </label>
                        </div>
                    </div>
                </div> -->

                <div class="row" style="margin-top: 20px;">
                    <div class="col-md-12 col-lg-12" style="text-align: center;">
                        <!-- <button type="button" class="btn btn-outline-info" [routerLink]="['giftfunds']" [queryParams]="{category: 'family',type: 'birthday', chosen: 'daughter'}"> 
                                CREATE REGISTRY
                            </button> -->
                        <button type="submit" id="registrybtn" class="btn btn-md btn-outline-info" disabled="disabled">
                            CREATE REGISTRY
                        </button>
                    </div>
                </div>
            </form:form>