<%@ page import="informados.noticia.Diario"%>
<table>
	<tbody>
		<g:each in="${diarioInstanceList}" status="i" var="diarioInstance">
			<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
				<g:if test="${diarioInstance?.nombre}">
					<td><span class="property-value" aria-labelledby="nombre-label"><g:link
							action="show" controller="diario" id="${diarioInstance.id}">
							<g:fieldValue bean="${diarioInstance}" field="nombre" />
						</g:link></span></td>						
				</g:if>
			</tr>
		</g:each>
	</tbody>
</table>