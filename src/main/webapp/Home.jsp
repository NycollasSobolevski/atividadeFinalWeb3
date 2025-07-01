<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Parkflow - Home</title>
	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

</head>
<body>

	<header class="main-header">
	  <div class="logo-container">
	    <div class="logo-brand">
	    	<img  src="images/ParkFlowLogo.png" alt="logo"/>
	      <span class="logo-text">PARKFLOW</span>
	    </div>
	  </div>
	</header>
	<main>
	
	<section class="page_top">
		<h1>Empresas</h1>
		<div class="counter_container"><h3> Empresas Cadastradas  </h3></div>
	</section>

	<section class="chart_section align">
	
		<div class="chart"></div>
		<div class="mini_table">
			<span class="table_header align">
				<h3>Instalações/Manutenções Agendadas</h3>
              	<button class="icon_btn" onclick="toggleModal()">
                 	<i class="material-icons">add</i>
                 </button>
			</span>
			<table>
				<tr> 
					<th>Empresa</th>
					<th>Tipo</th>
					<th>Data</th>
					<th></th>
				</tr>
				<c:forEach items="${maintenances}" var="item">
                  <tr>
                        <td>${item.getCompany().getName()}</td>
                        <td>${item.getMaintenceKind()}</td>
                        <td>${item.getSchedule()}</td>
                        <td>
                        	<button class="icon_btn" onclick="toggleEdit({
                        		'maintenceKind': '${item.getMaintenceKind() }',
                        		'observation':'${item.getObservations()}',
                        		'id':'${item.getId()}',
                        		'schedule':'${item.getSchedule()}',
                        		'companyCnpj':'${item.getCompany().getCnpj()}',
                        		})">
                        		<i class="material-icons">edit</i>
                        	</button>
                       	</td>
                    </tr>
				</c:forEach>
			</table>
		</div>
	
	</section>
	
	</main>
	
	<div class="modal" id="schedule_modal">
		<div class="modal-bg" onclick="toggleModal()"></div>	
		<div class="container">

			<div class="modal-top">
				<h2>Novo Agendamento</h2>
				<button class="icon_btn" onclick="toggleModal()">
					<i class="material-icons">
						close
					</i>
				</button>
			</div>
			
			<div class="content ">
				<form action="${pageContext.request.contextPath}/home" method="post" class="align" id="maintenanceForm">
					<input type="hidden" name="id" id="maintenanceId">
					<input type="hidden" name="action" id="maintenanceAction" value="ADD">

					<span>
						<label for="cnpj">CNPJ</label>
						<input type="text" name="cnpj" id="icnpj">
					</span>
					<div class="radio_area align">
					    <span>
					        <input type="radio" name="ikind" id="technicalVisit" value="Visita técnica" checked>
					        <label for="technicalVisit">Visita Técnica</label>
					    </span>
					    <span>
					        <input type="radio" name="ikind" id="instalation" value="Instalação">
					        <label for="instalation">Instalação</label>
					    </span>
					    <span>
					        <input type="radio" name="ikind" id="maintence" value="Manutenção">
					        <label for="maintence">Manutenção</label>
					    </span>
					</div>
					<span>
						<label for="date">Data</label>
						<input type="date" name="date" id="date">
					</span>
					<span>
						<label for="observation">Observação</label>
						<textarea name="observation" id="observation"></textarea>
					</span>
					<div class="btn_area align">
						<button type="submit">Salvar</button>
						<button onclick="toggledelete()" class="danger" id="deleteBtn">Deletar</button>
					</div>
				</form>
			</div>
			
		</div>
	</div>
	</body>
	
<script>
	const toggleModal = () => {
		const element = document.getElementById("schedule_modal");
		element.classList.toggle("visible");
		clearForm();
	}
	
	const toggleEdit = (value) => {
		const element = document.getElementById("schedule_modal");
	    element.classList.toggle("visible");

	    const form = document.getElementById("maintenanceForm");
	    const iid = document.getElementById("maintenanceId");
	    const icnpj = document.getElementById("icnpj"); 
	    const iobservation = document.getElementById("observation"); 
	    const idate = document.getElementById("date"); 
	    const deleteBtn = document.getElementById("deleteBtn");
	    const action = document.getElementById("maintenanceAction");
	    action.value = "UPDATE";

		iid.value = value.id;
	    icnpj.value = value.companyCnpj;
	    iobservation.value = value.observation; 
	    idate.value = value.schedule;         

	    
	    
	    const kindRadios = document.getElementsByName("kind");
	    for (const radio of kindRadios) {
	        if (radio.value === value.maintenceKind) {
	            radio.checked = true; 
	            break;
	        }
	    }
	    deleteBtn.classList.add("visible");	
	}
	
	const clearForm = () => {
		const form = document.getElementById("maintenanceForm");
		form.setAttribute("method", "post");
		const icnpj = document.getElementsByName("cnpj")[0];
		const ikind = document.getElementsByName("kind")[0];
		const iobservation = document.getElementById("observation");
		const idate = document.getElementsByName("date")[0];
	    const iid = document.getElementById("maintenanceId");
	    const action = document.getElementById("maintenanceAction");
	    action.value = "ADD";
		icnpj.value = ""
		ikind.value = ""
		iobservation.value = ""
		idate.value = ""
		iid.value = '';
		const deleteBtn = document.getElementById("deleteBtn");
		deleteBtn.classList.remove("visible");
	}
	
	const toggledelete = () => {
	    const action = document.getElementById("maintenanceAction");
	    action.value = "DELETE";
		const confirmed = confirm("Voce tem certeza que deseja excluir esse item? (" + iid.value + ")");
		if(!confirmed){
			toggleModal();
			return;
		}
	}
	
</script>
</html>