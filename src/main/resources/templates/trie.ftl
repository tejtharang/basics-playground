<html>
  <body>
    <pre class="mermaid">
      graph LR
       <#list model["relationships"] as relationship>
          ${relationship}
      </#list>
    </pre>

    <script type="module">
      import mermaid from 'https://cdn.jsdelivr.net/npm/mermaid@10/dist/mermaid.esm.min.mjs';
      mermaid.initialize({ startOnLoad: true });
    </script>
  </body>
</html>