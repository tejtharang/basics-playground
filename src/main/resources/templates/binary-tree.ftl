<html>
  <body>
    <pre class="mermaid">
      graph TD
       <#list model["nodes"] as node>
          <#if node.getPlainInt()?? && node.getLeft()?? && node.getLeft().getPlainInt()??>
                ${node.getPlainInt()} --> ${node.getLeft().getPlainInt()}
          </#if>
          <#if node.getPlainInt()?? && node.getRight()?? && node.getRight().getPlainInt()??>
                ${node.getPlainInt()} --> ${node.getRight().getPlainInt()}
          </#if>
      </#list>
    </pre>

    <script type="module">
      import mermaid from 'https://cdn.jsdelivr.net/npm/mermaid@10/dist/mermaid.esm.min.mjs';
      mermaid.initialize({ startOnLoad: true });
    </script>
  </body>
</html>