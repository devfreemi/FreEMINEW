<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

								<div class="registry-profiles">
									<i class="fas fa-list" style="color: aliceblue;"></i>
								</div>

								<div class="registry-outer">
									<c:choose>
										<c:when test="${ORDERHISTORY == 'SUCCESS' }">
											<div id="accordion">
												<table class="table table-sm">
													<caption>Mutual Funds Purchase History</caption>
													<thead class="#3949ab indigo darken-1 white-text">
														<tr>
															<th scope="col" valign="middle">Mutual Fund</th>
															<th scope="col" valign="middle">Invested Amount (Rs.)</th>
															<!-- <th scope="col" valign="middle">XIRR</th> -->
															<!-- <th scope="col" valign="middle">Weighted</th>
															<th scope="col" valign="middle">NAV</th> -->
															<th scope="col" valign="middle">Market Value (Rs.)</th>
															<th scope="col" valign="middle">VIEW</th>
															<!-- <th scope="col">XIRR</th>
													<th scope="col">Accumulated Amount</th>
													<th scope="col">Topup SIP</th>
													<th scope="col">Action</th> -->
														</tr>
													</thead>

													<tbody>

														<c:forEach var="listVar" items="${allfundsview}"
															varStatus="loop">
															<tr>
																<td style="width: 14rem;" valign="middle"><img
																	src="<c:url value="${contextcdn}/resources/images/partnerlogo/mf/${listVar.amcicon }"/>"
																	class="img-fluid" style="width: 3rem; float: left;">
																	${listVar.fundName }</td>
																<td valign="middle"><fmt:formatNumber type="number"
																		pattern="#,##,##,##,###.0000" minFractionDigits="1"
																		minIntegerDigits="1" maxFractionDigits="4"
																		value="${listVar.collaboratedAmount }" /></td>
																<!-- <td valign="middle"></td> -->
																<%-- <td valign="middle"><%=counter%></td> --%>
																<!-- <td valign="middle"></td>
																<td valign="middle"></td> -->

																<c:choose>
																	<c:when
																		test="${listVar.collaboratedMarketValue > listVar.collaboratedAmount}">
																		<td valign="middle"><span style="color: #30c51e;">
																				<fmt:formatNumber type="number"
																					pattern="#,##,##,##,###.00" minFractionDigits="1"
																					minIntegerDigits="1" maxFractionDigits="2"
																					value="${listVar.collaboratedMarketValue }" />
																		</span></td>
																	</c:when>
																	<c:when
																		test="${listVar.collaboratedMarketValue < listVar.collaboratedAmount}">
																		<td valign="middle"><span style="color: red;"><fmt:formatNumber
																					type="number" pattern="#,##,##,##,###.00"
																					minFractionDigits="1" minIntegerDigits="1"
																					maxFractionDigits="2"
																					value="${listVar.collaboratedMarketValue }" /></span></td>
																	</c:when>
																	<c:otherwise>
																		<td valign="middle"><span style="color: grey;"><fmt:formatNumber
																					type="number" pattern="#,##,##,##,###.00"
																					minFractionDigits="1" minIntegerDigits="1"
																					maxFractionDigits="2"
																					value="${listVar.collaboratedMarketValue }" /></span></td>
																	</c:otherwise>
																</c:choose>

																<td style="text-align: center;"><a class=""
																	data-toggle="collapse" href="#collapse${loop.index}"
																	<%-- data-toggle="collapse" href="#collapse<%=counter%>" --%>
																	role="button"
																	aria-expanded="false"
																	aria-controls="collapse${loop.index}"> <span
																		<%-- aria-controls="collapse<%=counter%>">  <span --%>
																		class="fas fa-chevron-right"
																		id="rotate"></span>
																</a></td>

															</tr>
															<tr>
																<td colspan="7" style="padding: 0px;">
																	<div class="collapse" id="collapse${loop.index}"
																		<%-- <div class="collapse" id="collapse<%=counter%>" --%>
																		style="margin: .2rem;">
																		<div class="card card-body" style="padding: 0;">
																			<table class="table table-sm">
																				<thead class="#00796b teal darken-2 white-text">
																					<tr class="mftransdetailhead">
																						<th scope="col" style="width: 20rem;"
																							valign="middle">Folio <br> Scheme
																							Code/Name
																						</th>
																						<th scope="col" valign="middle">Invested
																							Value (Rs.)</th>
																						<th scope="col" valign="middle">Bal. Units</th>
																						<th scope="col" valign="middle">NAV (As on Date)</th>
																						<th scope="col" valign="middle">Current Value
																							(Rs.)</th>
																						<th scope="col" valign="middle">Action</th>
																					</tr>
																				</thead>
																				<tbody>

																					<c:forEach var="folioList"
																						items="${listVar.karvyFolioList}"
																						varStatus="innerloop">
																						<tr>
																							<td valign="middle">${folioList.folioNumber }<br>${folioList.fundDescription }
																								/ ${folioList.bsemfschemeCode }
																							</td>
																							<td valign="middle"><fmt:formatNumber
																									type="currency" pattern="#,##,##,##,###.0000"
																									minFractionDigits="1" minIntegerDigits="1"
																									maxFractionDigits="4"
																									value="${folioList.invAmount }" /></td>
																							<td valign="middle"><fmt:formatNumber
																									type="number" pattern="#,##,##,##,###.0000"
																									minFractionDigits="1" minIntegerDigits="1"
																									maxFractionDigits="4"
																									value="${folioList.units }" /></td>
																							<td valign="middle">
																								<span>${folioList.nav }</span>
																								<br>
																								<span style="font-size: 9px;color: grey;">(${folioList.navdate })</span>
																							</td>
																							<c:choose>
																								<c:when
																									test="${folioList.marketValue > folioList.invAmount}">
																									<td valign="middle"><span
																										style="color: #30c51e;"> <fmt:formatNumber
																												type="currency"
																												pattern="#,##,##,##,###.0000"
																												minFractionDigits="1" minIntegerDigits="1"
																												maxFractionDigits="4"
																												value="${folioList.marketValue }" /></span></td>
																								</c:when>
																								<c:when
																									test="${folioList.marketValue < folioList.invAmount}">
																									<td valign="middle"><span
																										style="color: red;"><fmt:formatNumber
																												type="currency"
																												pattern="#,##,##,##,###.0000"
																												minFractionDigits="1" minIntegerDigits="1"
																												maxFractionDigits="4"
																												value="${folioList.marketValue }" /></span></td>
																								</c:when>
																								<c:otherwise>
																									<td valign="middle"><span
																										style="color: grey;"><fmt:formatNumber
																												type="currency"
																												pattern="#,##,##,##,###.0000"
																												minFractionDigits="1" minIntegerDigits="1"
																												maxFractionDigits="4"
																												value="${folioList.marketValue }" /></span></td>
																								</c:otherwise>
																							</c:choose>

																							<td valign="middle">
																								<div class="btn-group">
																									<button type="button"
																										class="btn btn-secondary dropdown-toggle btn-sm"
																										data-toggle="dropdown" aria-haspopup="true"
																										aria-expanded="false"
																										style="font-size: 11px; padding: 5px; width: 7rem;">TRANSACT</button>
																									<div class="dropdown-menu dropdown-menu-right">
																										<button class="dropdown-item" type="button"
																											style="font-size: 12px; color: #238019; font-weight: 600;"
																											onclick="AdditionalPurchase('${folioList.folioNumber}','${folioList.bsemfschemeCode }','${folioList.trasanctionType }','${listVar.rtaAgent }','${folioList.channelProductCode }')">
																											Invest More <i class="fas fa-arrow-left"></i>
																										</button>

																										<c:if test="${folioList.invAmount > 0 }">
																											<button class="dropdown-item" type="button"
																												style="font-size: 12px; color: #da2323; font-weight: 600;"
																												onclick="MFRedeem('${folioList.folioNumber}','${folioList.bsemfschemeCode }','${folioList.trasanctionType }','${listVar.rtaAgent }','${folioList.channelProductCode }')">
																												Redeem <i class="fas fa-arrow-right"></i>
																											</button>
																										</c:if>
																									</div>
																								</div>
																							</td>
																						</tr>
																					</c:forEach>


																				</tbody>
																			</table>
																		</div>
																	</div>

																</td>


															</tr>

															<%-- 	<%=counter+=1 %> --%>
														</c:forEach>


													</tbody>

												</table>

												<p style="font-size: 11px;">
													**Please contact admin at <a
														href="mailto:support@freemi.in?Subject=MF%20fund%20balance%20incorrect">support@freemi.in</a>
													if the data is incorrect or not updated.
												</p>
											</div>
										</c:when>
										<c:when test="${ORDERHISTORY == 'ERROR' }">
											<span>Failed to fetch your data. Please try after
												sometime</span>
										</c:when>
										<c:when test="${ORDERHISTORY == 'EMPTY' }">
											<span>You have not yet purchased any funds.</span>
											<a href="/products/mutual-funds/funds-explorer">Start
												investing</a>
										</c:when>
										<c:otherwise>
											<span>Something went wrong. Kindly contact admin.</span>
										</c:otherwise>
									</c:choose>
									<div style="overflow-x: auto;"></div>
								</div>